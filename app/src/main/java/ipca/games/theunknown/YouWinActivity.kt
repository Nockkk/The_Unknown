package ipca.games.theunknown

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_game_over.*
import kotlinx.android.synthetic.main.activity_game_over.textViewScore
import kotlinx.android.synthetic.main.activity_main.imageExit
import kotlinx.android.synthetic.main.activity_main.imageRestart
import kotlinx.android.synthetic.main.activity_you_win.*
import kotlin.system.exitProcess

class YouWinActivity : AppCompatActivity() {

    private lateinit var boss1Animation: AnimationDrawable
    private lateinit var enemyAnimation: AnimationDrawable
    private lateinit var spaceshipAnimation: AnimationDrawable
    private lateinit var shipAnimation: AnimationDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation =  (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_you_win)

        textHighScoreWin.isVisible = false

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                    // Set the content to appear under the system bars so that the
                    // content doesn't resize when the system bars hide and show.
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    // Hide the nav bar and status bar
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
        }

        findViewById<ImageView>(R.id.boss_1_anim).apply {
            setBackgroundResource(R.drawable.boss_1_anim)
            boss1Animation = background as AnimationDrawable
        }

        findViewById<ImageView>(R.id.enemy_anim).apply {
            setBackgroundResource(R.drawable.enemy_anim)
            enemyAnimation = background as AnimationDrawable
        }

        findViewById<ImageView>(R.id.spaceship_anim).apply {
            setBackgroundResource(R.drawable.spaceship_anim)
            spaceshipAnimation = background as AnimationDrawable
        }

        findViewById<ImageView>(R.id.ship_anim).apply {
            setBackgroundResource(R.drawable.ship_anim)
            shipAnimation = background as AnimationDrawable
        }

        imageExit.setOnClickListener {
            moveTaskToBack(true)
            android.os.Process.killProcess(android.os.Process.myPid())
            exitProcess(1)
        }

        imageRestart.setOnClickListener {
            val intent = Intent(this@YouWinActivity, GameActivity1::class.java)
            startActivity(intent)
        }

        textViewScore.text = getString(R.string.score) + intent.getIntExtra("SCORE",0).toString()
        var finalscore = intent.getIntExtra("SCORE",0)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("users").child(FirebaseAuth.getInstance().currentUser!!.uid).child("highscore")

        if(finalscore > GameActivity1.highscore!!) {
            myRef.setValue(finalscore)
            textHighScoreWin.isVisible = true
        }

        lateinit var mShare : FloatingActionButton
        mShare = findViewById(R.id.fabSave)
        mShare.setOnClickListener {
            val myintent = Intent(Intent.ACTION_SEND)
            myintent.type = "type/palin"
            val shareBody = "Score"
            val shareSub = finalscore.toString()
            myintent.putExtra(Intent.EXTRA_SUBJECT, shareBody)
            myintent.putExtra(Intent.EXTRA_TEXT, shareSub)
            startActivity(Intent.createChooser(myintent, "Share your App"))

        }


    }

    override fun onStart() {
        super.onStart()
        boss1Animation.start()
        enemyAnimation.start()
        spaceshipAnimation.start()
        shipAnimation.start()
    }

}