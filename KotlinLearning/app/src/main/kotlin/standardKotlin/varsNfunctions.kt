fun main() {
    // vars
    var firstName = "Bogdan";
    val lastName = "Vladutu";

    firstName = "Dragos";
    // lastName = "Vladutul"; // val can not be reassigned

    println(firstName); // Dragos
    println(lastName); // Vladutu

    // Null
    data class Plane(val name: String) {
        fun start() {
            print("${name}: Start Engine");
        }
    }
    var plane: Plane? = null;
    plane?.start(); // nothing to print
    var planeOrTest = plane ?: "Test";
    println(planeOrTest); // Test

    plane = Plane("Boeing");
    plane?.start(); // Boeing: Start Engine

    println("\n");
    // Generics
    class PrintData<T>(paramInput: T) {
        val value: T = paramInput;
    }
    println(PrintData("Asta e string").value); // Asta e string
    println(PrintData(23).value); // 23

    // Functions
    println("\n")
    // Simple call
    fun getFirstLetter(stringParam: String): Char {
        return stringParam[0];
    }

    println("First letter from abc is ${getFirstLetter("abc")}")

    println("\n")
    // Functions overloading
    fun sumNumbers(a: Int, b: Int) = a + b;
    fun sumNumbers(a: Int, b: Int, c:Int) = a + b + c;
    fun sumNumbers(a: Double, b: Double) = a + b;

    println("Sum 2 + 3 = ${sumNumbers(2,3)}") // call the first sumNumbers
    println("Sum 2 + 3 + 5 = ${sumNumbers(2,3, 5)}") // call the second sumNumbers
    println("Sum 2.0 + 3.0 = ${sumNumbers(2.0, 3.0)}") // call the third sumNumbers

    println("\n")
    // Functions default values
    fun printName(firstName: String = "Bogdan", lastName: String = "Vladutu") = println("Name is: ${firstName} ${lastName}")

    printName(); // Bogdan Vladutu
    printName(lastName = "Dragos"); // Bogdan Dragos
    printName("Dragos", "Bogdan"); // Dragos Bogdan

    println("\n")
    // varargs
    fun printNumbers(a:Int, b: Int, vararg xs: Int) {
        print("First number is: ${a}, second number is: ${b}, the rest of the numbers are: ");
        print(xs.joinToString());
    }
    printNumbers(1,2,3,4,5,6); // First number is: 1, second number is: 2, the rest of the numbers are: 3, 4, 5, 6

}
