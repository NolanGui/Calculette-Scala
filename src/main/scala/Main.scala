@main def interactiveCalculator(): Unit = {
  println("Bienvenue dans la calculatrice interactive !")
  println("Tapez 'exit' pour quitter.")
  
  var continue = true
  
  while (continue) {
    println("\nSaisissez une opération mathématique (ex : 5 + 3) :")
    val input = scala.io.StdIn.readLine()
    
    if (input.trim.equalsIgnoreCase("exit")) {
      continue = false
      println("Merci d'avoir utilisé la calculatrice. À bientôt !")
    } else {
      try {
        val result = calculate(input)
        println(s"Résultat : $result")
      } catch {
        case e: Exception => println(s"Erreur : ${e.getMessage}")
      }
    }
  }
}

def calculate(input: String): Double = {
  val trimmedInput = input.trim
  val pattern = """(-?\d+(\.\d+)?)\s*([\+\-\*/])\s*(-?\d+(\.\d+)?)""".r

  trimmedInput match {
    case pattern(num1, _, operator, num2, _) =>
      val a = num1.toDouble
      val b = num2.toDouble
      operator match {
        case "+" => a + b
        case "-" => a - b
        case "*" => a * b
        case "/" => 
          if (b == 0) throw new ArithmeticException("Division par zéro interdite.")
          else a / b
        case _ => throw new IllegalArgumentException(s"Opérateur invalide : $operator")
      }
    case _ => throw new IllegalArgumentException("Format d'entrée incorrect. Utilisez le format : nombre opérateur nombre (ex : 5 + 3).")
  }
}