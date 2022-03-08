
import scala.io.Source

object Main {
  def main (args: Array[String]): Unit = {
   //чтение из файла и создание объектов(квадратов) с парметрами
    var squareArray = List[Square]()
    Source.fromFile("D:/input.txt").getLines.foreach { x => println(x)
     squareArray=new Square(number(x))::squareArray
    };
    squareArray=squareArray.reverse
    //преобразование списка в массив для доступа ко всем элементам
  val resultSquare = squareArray.toArray

    recursive(resultSquare,resultSquare.length)
  }
  //метод преобразующий строковые данные в массив чисел для заполнения конструктора объекта
  def number(first: String): Array[Int] = {
    val stringArray: Array[String]=first.split(" ")
    val intArray:Array[Int]= new Array[Int](stringArray.length)
    for (counter <- 0 to stringArray.length-1){
      intArray(counter)=stringArray(counter).toInt
    }
    return intArray;
  }
    //логика поиска решений
  def reconciliation(squares:Array[Square]): Unit = {
    if (
      ((squares(0).downRight + squares(1).downLeft + squares(3).upRight + squares(4).upLeft)!=10)
        || ((squares(2).downRight+squares(3).downLeft+squares(6).upRight+squares(7).upLeft)!=10)
        || ((squares(3).downRight+squares(4).downLeft+squares(7).upRight+squares(8).upLeft)!=10)
        || ((squares(9).upLeft+squares(5).downLeft+squares(8).upRight+squares(4).downRight)!=10)
        || ((squares(10).upRight+squares(11).upLeft+squares(7).downRight+squares(8).downLeft)!=10)

        || ((squares(0).upRight + squares(1).upLeft)>10)
        || ((squares(0).downLeft + squares(2).upRight + squares(3).upLeft) > 10)
        || ((squares(1).downRight+squares(4).upRight+squares(5).upLeft)>10)
        ||((squares(2).downLeft+squares(6).upLeft)>10)
        || ((squares(9).upRight+squares(5).downRight)>10)
        || ((squares(9).downLeft+squares(8).downRight+squares(11).upRight)>10)
        || ((squares(10).downRight+squares(11).downLeft)>10)
        || ((squares(6).downRight+squares(7).downLeft+squares(10).upLeft)>10)
    )
    {

    }else {
      for (counter <- 0 to squares.length-1){
       print(squares(counter).upLeft+" "+squares(counter).upRight+" "+squares(counter).downLeft+" "+squares(counter).downRight+"\n")
      }
      println()
    }
  }
    //рекурсивный алгоритм перебора комбинаций расположения элементов массива
  def recursive(elements:Array[Square], n:Int): Unit = {
    if(n==1){
      reconciliation(elements)
    }else {
      for (counter <- 0 to n-2){
        recursive(elements,n-1)
        if(n % 2 == 0){
          swap(elements,counter,n-1)

        }else{
          swap(elements,0,n-1)
        }
      }
      recursive(elements, n-1)
    }
  }
  //метод перестановки элемента массива
  def swap(s:Array[Square], a:Int, b:Int): Unit = {
    var t:Square=s(a)
    s(a)=s(b)
    s(b)=t

  }

}
