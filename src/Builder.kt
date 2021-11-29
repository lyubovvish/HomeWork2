//Паттерн Builder — это паттерн проектирования, который позволяет поэтапно создавать
// сложные объекты с помощью четко определенной последовательности действий.
//продемонстрировано как мы можем реализовать шаблон проектирования

class myBuilder{
    private var Name:String? = null

    fun setName(Name:String){
        this.Name = Name
    }
    fun getName():String?{
        return this.Name
    }
}

class myClass{

    var m:myBuilder
    constructor(myBuilder:myBuilder) {
        this.m = myBuilder
    } // реализация класса builder
    fun build():String{
        return ("This is the example of the builder pattern")
    }
}

fun main(args: Array<String>) {
    var myBuilder = myBuilder()
    myBuilder.setName("TutorialsPoint")

    //передача небольшой единицы объектов
    var mainObj = myClass(myBuilder)
    println(mainObj.build())
}