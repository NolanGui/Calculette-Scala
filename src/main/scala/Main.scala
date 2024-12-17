@main def interactiveCalculator(): Unit = {
  greetUser()
  runCalculatorLoop()
  farewellUser()
}

def greetUser(): Unit = {
  println("Bienvenue dans la calculatrice interactive !")
  println("Tapez 'exit' pour quitter.")
}

def farewellUser(): Unit = {
  println("Merci d'avoir utilisé la calculatrice. À bientôt !")
}

def runCalculatorLoop(): Unit = {
  var isRunning = true
  while (isRunning) {
    val input = promptUserInput()
    if (shouldExit(input)) {
      isRunning = false
    } else {
      processInput(input)
    }
  }
}

def promptUserInput(): String = {
  println("\nSaisissez une opération mathématique (ex : 5 + 3) :")
  scala.io.StdIn.readLine()
}

def shouldExit(input: String): Boolean = {
  input.trim.equalsIgnoreCase("exit")
}

def processInput(input: String): Unit = {
  try {
    val result = calculate(input)
    println(s"Résultat : $result")
  } catch {
    case e: Exception => println(s"Erreur : ${e.getMessage}")
  }
}

def calculate(input: String): Double = {
  val pattern = """(-?\d+(\.\d+)?)\s*([\+\-\*/])\s*(-?\d+(\.\d+)?)""".r
  input.trim match {
    case pattern(num1, _, operator, num2, _) => performOperation(num1.toDouble, operator, num2.toDouble)
    case _ => throw new IllegalArgumentException("Format d'entrée incorrect. Utilisez le format : nombre opérateur nombre (ex : 5 + 3).")
  }
}

def performOperation(a: Double, operator: String, b: Double): Double = {
  operator match {
    case "+" => a + b
    case "-" => a - b
    case "*" => a * b
    case "/" if b != 0 => a / b
    case "/" => throw new ArithmeticException("Division par zéro interdite.")
    case _ => throw new IllegalArgumentException(s"Opérateur invalide : $operator")
  }
}
