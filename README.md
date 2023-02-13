# OTUS Homework

## Lucky Tickets Counter

### Run app

1. Build - `./gradlew clean build`
2. Run - `java -jar build/libs/lucky-tickets-counter-*.jar <number of digits in ticket>`

### Test app

1. Default tests - `./gradlew test`
2. Custom tests
   `./gradlew -Dru.potatophobe.luckyticketscounter.LuckyTicketsCounterTest.dir=<Dir with test.*.in|out> test`
