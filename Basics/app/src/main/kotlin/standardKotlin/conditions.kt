fun main() {
    // for loop
    for (i in 1..10) {
        print("${i} ");
    }
    // 1, 2, 3, 4, 5, 6, 7, 8, 9, 10

    println()
    for (i in 1 until 10) {
        print("${i} ");
    }
    // 1, 2, 3, 4, 5, 6, 7, 8, 9

    println()
    for (i in 10 downTo 0) {
        print("${i} ")
    }
    // 10, 9, 8, 7, 6, 5, 4, 3, 2, 1

    println()
    for (i in 10 downTo 0 step 2) {
        print("${i} ")
    }
    // 10 8 6 4 2 0

    println()
    val numbers = 1..10;
    println(numbers) // 1..10
    for (i in numbers) {
        print("${i} ")
    }
    // 1, 2, 3, 4, 5, 6, 7, 8, 9, 10

    println()
    for (i in 0..10) {
        if (i in 3..7) continue;
        print("${i} ");
    }
    // 0, 1, 2, 8, 9, 10

    println("\n")
    var condition = true;
    outer@ while (condition) {
        var iterator = 10;
        while (iterator > 0) {
            print("${iterator} ");
            iterator--;
            if (iterator == 7) break@outer;
        }

        println("Never here");
    }
    // 10, 9, 8, 7

    println("\n")
    val myNumber = 7;
    when (myNumber) {
        in 6..10 -> print("Number is in rage 6..10")
        else -> print("Default branch")
    }
    // Number is in rage 6..10

    println()
    val mySecondNumber = 7;
    when {
        mySecondNumber > 5 -> print("Number is greater than 5")
        else -> print("Default branch")
    }
    // Number is greater than 5

    println()
    class MyFirstName(val firstName: String);
    val myFirstName = MyFirstName("Bogdan");
    when (myFirstName) {
        is MyFirstName -> print("Is of type MyFirstName")
        else -> print("Default branch")
    }
    // Is of type MyFirstName

    println("\n")
    // let, also
    data class Car(var doors: Int);
    var audi: Car? = null;
    val isCoupe = audi?.let { it.doors == 2 };
    println(isCoupe); // null
    audi = Car(4);
    val isAudiCoupe = audi?.let { it.doors == 2 };
    println(isAudiCoupe); // false

    var isAudiOrNotCoupe = audi?.also { it.doors = 2 }?.let { it.doors == 2 }
    println(isAudiOrNotCoupe); // true

}