// общая команда рисования "чего-то" на "Canvas"
abstract class DrawCommand(private val canvas: Canvas) {
    //состояние перед выполнением команды - список уже нарисованных элементов
    private var preCommandState = listOf<Shape>()

    abstract fun execute()
    // сохранение
    fun saveState() {
        preCommandState = canvas.shapes.toList()
    }

    // отмена команды, установка состояния холста до выполнения
    fun undo() {
        println("undo ${this}")
        canvas.shapes = preCommandState.toMutableList()
    }
}
// интерфейсы рисоваемых фигур
interface Shape
data class Line(val length: Int) : Shape
data class Circle(val diameter: Int) : Shape
// команды для рисования фигур, получения параметров и приемника (Canvas)
class DrawLine(private val length: Int, private val canvas: Canvas) : DrawCommand(canvas) {
    override fun execute() {
        saveState()
        canvas.draw(Line(length))
    }
}
class DrawCircle(private val diameter: Int, private val canvas: Canvas) : DrawCommand(canvas) {
    override fun execute() {
        saveState()
        canvas.draw(Circle(diameter))
    }
}

// Приемник
class Canvas {
    // текущее состояние
    var shapes: MutableList<Shape> = mutableListOf()
    //  добавление фигур в список
    fun draw(shape: Shape) {
        println("drawing a $shape")
        shapes.add(shape)
    }
}

fun main() {
    val canvas = Canvas()
    val commandsHistory = mutableListOf<DrawCommand>()

    // после выполнения команды она сохраняется в истории
    val drawLine = DrawLine(2, canvas)
    drawLine.execute()
    commandsHistory.add(drawLine)

    val drawCircle = DrawCircle(1, canvas)
    drawCircle.execute()
    commandsHistory.add(drawCircle)

    val drawLongLine = DrawLine(10, canvas)
    drawLongLine.execute()
    commandsHistory.add(drawLongLine)

    val drawBigCircle = DrawCircle(12, canvas)
    drawBigCircle.execute()
    commandsHistory.add(drawBigCircle)

    println("current shapes: ${canvas.shapes}")
    println("--- undo last 2 ---")
    // reverting last 2 commands
    commandsHistory.removeLast().undo()
    commandsHistory.removeLast().undo()
    println("current shapes: ${canvas.shapes}")
}