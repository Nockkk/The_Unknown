package ipca.games.theunknown

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import java.util.*

class Boss1Bullet_1 {

    var x: Int = 0
    var y: Int = 0
    var speed: Int = 0

    private var maxY = 0
    private var minY = 0
    private var maxX = 0
    private var minX = 0

    val collissionDetection : Rect
    var bitmap : Bitmap

    var generator = Random()


    constructor(context: Context,
                borderWidth: Int,
                borderHeight: Int, boss1: Boss1
    ) {

        maxX = borderWidth
        maxY = borderHeight
        minX = 0
        minY = 100
        speed = 20


        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.enemy_bullet)

        x = boss1.x + (boss1.bitmap.width / 2) - (bitmap.width / 2)
        y = boss1.y + boss1.bitmap.height

        collissionDetection = Rect(x, y, bitmap.width, bitmap.height)

    }

    fun update() {

        y += speed
        collissionDetection.set(x,y, x + bitmap.width, y + bitmap.height)


        /*if (y + 50.0f < 0 && y + 50.0f > -500.0f) {
            speed = 10
            x = player.x + (player.bitmap.width / 2) - bitmap.width
            y = player.y
        }*/
    }
}