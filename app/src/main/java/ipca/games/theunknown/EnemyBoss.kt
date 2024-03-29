package ipca.games.theunknown

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Rect
import java.security.AccessControlContext
import java.util.*
import kotlin.math.max

class EnemyBoss {
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
    var color: Int

    constructor(context: Context, borderWidth : Int, borderHeight : Int, boss2: Boss2){

        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.enemy_0)

        maxY = borderHeight - bitmap.height
        maxX = borderWidth - bitmap.width

        speed = generator.nextInt(10) + 10

        x = boss2.x + generator.nextInt(boss2.resized.width)
        //x = generator.nextInt(maxX)
        y = boss2.resized.height

        color = Color.WHITE

        collissionDetection = Rect(x, y, bitmap.width, bitmap.height)
    }

    fun update(boss2: Boss2){

        if(color == Color.WHITE){
            y += speed
        }

        if(color == Color.RED){
            y += speed * 2
        }

        if(color == Color.GREEN){
            y += speed / 2
        }


        if (y - bitmap.height * 2 > maxY) {

            x = boss2.x + generator.nextInt(boss2.resized.width)
            //x = generator.nextInt(maxX)
            speed = generator.nextInt(10) + 10
            y = boss2.resized.height
        }

        collissionDetection.set(x,y, x + bitmap.width, y + bitmap.height)
    }
}