# Pocker kata excercice

Pocker projecty prerequite:

*   The JRE flavor requires JDK 16 or higher.
*   Gradle as build tools.

## Installing project dependencies

To install projects using gradle, use the following:

```shell
./gradlew build
```

## Run project unit tests

To install project unit tests, use the following:

```shell
./gradlew test
```

## Algorithm explanation 
The solution is organized by several steps:

1. Initiate the cards with correct order.

```java
Hand(Set<Card> cards) {
    if (cards.size() != 5) {
        throw new IllegalArgumentException("Invalid cards list input");
    }
    this.cards = cards.stream()
            .sorted(Card.COMPARATOR)
            .collect(Collectors.toCollection(LinkedHashSet::new));
    this.handScoringList = initHandScoringList();
}
```
The order is based with a card priority:
```java
record Card(CardValue value, CardSuit cardSuit) {
    static final Comparator<Card> COMPARATOR = Comparator.comparing(card -> card.value.priority());

```
Here is how we define a card priority:
```java
private static int asPriority(String value) {
    if (isNumeric(value)) {
        return parseInt(value) - 1;
    }
    return switch (value.toUpperCase(Locale.ENGLISH)) {
        case "T" -> 9;
        case "J" -> 10;
        case "Q" -> 11;
        case "K" -> 12;
        case "A" -> 13;
        default -> throw new IllegalStateException("Unexpected value: " + value);
    };
}
```
2. Process a hard hand to calculate hand stats.
   See `Hand` class:
```java
Rank doScore() {
    HandStats handStats = doAnalyseHand();
    return handScoringList.stream()
        .map(handScoring -> handScoring.doScoring(handStats))
        .filter(rank -> rank != Rank.NONE)
        .findFirst()
        .orElse(Rank.NONE);
}

private HandStats doAnalyseHand() {
    var handStats = HandStats.initialize();
    for (Card card : cards) {
        handStats = handStats.analyse(card);
    }
    return handStats;
}
```
Here is `HandStats` structure:
```java
record HandStats(int totalScore, boolean hasSameSuite, boolean hasSequentialSuite,
                   Card previewsCard,
                   Map<CardValue, Integer> cardValueCounts) {

    static HandStats initialize() {
        return new HandStats(0, true, true, null,
                Map.copyOf(new HashMap<>(5)));
    }

    HandStats analyse(Card card) {
        var totalScore = this.totalScore + card.cardScore();;
        var cardValueCounts = new HashMap<>(this.cardValueCounts);
        var previewsCard = this.previewsCard;
        var hasSameSuite = this.hasSameSuite;
        var hasSequentialSuite = this.hasSequentialSuite;
        cardValueCounts.compute(card.value(), (k, v) -> v == null ? 1 : v + 1);
        if (previewsCard != null) {
            hasSameSuite &= card.hasSameSuit(previewsCard);
            hasSequentialSuite &= card.equalsPreviewsPriority(previewsCard);
        }
        previewsCard = card;
        return new HandStats(totalScore, hasSameSuite, hasSequentialSuite, previewsCard, Map.copyOf(cardValueCounts));
    }
}
```

3. Then we pass a `HandStats` instance to each `HandScoring` instances (those also ordered by priority), the algorithm stop when we found a nonempty output (instance of `Rank`), otherwise we return an empty value:
```java
handScoringList.stream()
    .map(handScoring -> handScoring.doScoring(handStats))
    .filter(rank -> rank != Rank.NONE)
    .findFirst()
    .orElse(Rank.NONE);
```
`HandScoring` list order maintained on `Hand` instance creation:
```java
private static List<HandScoring> initHandScoringList() {
    var handScoring = new LinkedList<HandScoring>();
    handScoring.add(new HandScoring.RoyalFlush());
    handScoring.add(new HandScoring.StraightFlush());
    handScoring.add(new HandScoring.FourOfKind());
    handScoring.add(new HandScoring.FullHouse());
    handScoring.add(new HandScoring.Flush());
    handScoring.add(new HandScoring.Straight());
    handScoring.add(new HandScoring.ThreeOfKind());
    handScoring.add(new HandScoring.TwoPair());
    handScoring.add(new HandScoring.OnePair());
    handScoring.add(new HandScoring.Highest());
    return unmodifiableList(handScoring);
}
```

Finally we got a subclass of `Rank` instance that encapsulate the rank of the player hand (royal flush, straight flush, four of king...).

4. Then to identify the winner :
We delegate this calculation to `PlayerScore`.
```java

SetScore whoWins(PlayerScore opponent) {
   if (shouldCalculateHighCardScore(opponent)) {
       return calculateHighCardScoreWinner(opponent);
   } else {
       return calculateTotalScore(opponent);
   }
}
```




