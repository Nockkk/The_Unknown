package ipca.games.theunknown

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import java.util.*

class Boss1 {
    var bitmap : Bitmap
    var x : Int = 0
    var y : Int = 0
    var speed : Int = 0
    var generator = Random()

    private val gravity : Int = -10
    private var min_speed = 1
    private var max_speed = 20
    private var maxY = 0
    private var maxX = 0
    private var minX = 0
    private var minY = 0
    val collissionDetection : Rect

    constructor(context: Context, borderWidth : Int, borderHeight : Int){

        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.boss_1_0)


        maxY = borderHeight - bitmap.height
        maxX = borderWidth - bitmap.width

        y = 0
        x = (borderWidth / 2)

        speed = generator.nextInt(10) + 10

        collissionDetection = Rect(x, y, bitmap.width, bitmap.height)
    }

    fun update(){
        x += speed

        if(x < minX) {
            speed = -speed
        }
        if (x > maxX) {
            speed = -speed
        }

        collissionDetection.set(x,y, x + bitmap.width, y + bitmap.height)
    }
}