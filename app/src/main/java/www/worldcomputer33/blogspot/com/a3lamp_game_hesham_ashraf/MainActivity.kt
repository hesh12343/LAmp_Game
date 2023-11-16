package www.worldcomputer33.blogspot.com.a3lamp_game_hesham_ashraf
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.media.SoundPool

class MainActivity : AppCompatActivity() {
    private val maxChances = 3
    private var lampOn = false
    private var lampNum = 1
    private lateinit var soundPool: SoundPool
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        soundPool = SoundPool.Builder().build()
        soundPool.load(this, R.raw.lookout, 1)
        var lamp = findViewById<ImageView>(R.id.lamp1)
        val image = findViewById<ImageView>(R.id.image)
        val button = findViewById<Button>(R.id.btn)
        image.setOnLongClickListener {
            val maxVolume = 10.0f
            val mediaPlayer = MediaPlayer.create(this, R.raw.broke)
            mediaPlayer.setVolume(maxVolume, maxVolume)
            image.setImageResource(R.drawable.lamp_off)
            lamp.setImageResource(R.drawable.lamp_broke)
            mediaPlayer.start()
            if (lampNum == maxChances - 1) {
                soundPool.play(1, maxVolume, maxVolume, 1, 0, 1.0f)
            }

            if (lampNum == maxChances) {
                button.visibility = View.VISIBLE

            }
            lampNum++
            lampOn = false
            lamp = when(lampNum) {
                1 -> findViewById(R.id.lamp1)
                2 -> findViewById(R.id.lamp2)
                3 -> findViewById(R.id.lamp3)
                //4 -> lamp = findViewById(R.id.lamp4)
                else -> findViewById(R.id.lamp1)
            }
            true
        }
    }
    fun onClickLamp(view:View)
    {
         var maxVolume = 10.0f
        val mediaPlayer = MediaPlayer.create(this,R.raw.click)
        mediaPlayer.setVolume(maxVolume, maxVolume)
        val imageView = view as ImageView
        var lamp = findViewById<ImageView>(R.id.lamp1)
        when(lampNum)
        {
            1 -> lamp = findViewById(R.id.lamp1)
            2 -> lamp = findViewById(R.id.lamp2)
            3 -> lamp = findViewById(R.id.lamp3)
        }
        if (!lampOn && lampNum != 5)
        {
            imageView.setImageResource(R.drawable.lamp_on)
            lamp.setImageResource(R.drawable.lamp_on)

            lampOn = !lampOn
            mediaPlayer.start()
        }
        else if (lampOn && lampNum != 5)
        {
            imageView.setImageResource(R.drawable.lamp_off)
            lamp.setImageResource(R.drawable.lamp_off)

            lampOn = !lampOn
            mediaPlayer.start()
        }
    }
    fun onBtnClick(view:View)
    {
        val imageView = findViewById<ImageView>(R.id.image)
        val button = view as Button
        imageView.setImageResource(R.drawable.lamp_off)
        findViewById<ImageView>(R.id.lamp1).setImageResource(R.drawable.lamp_off)
        findViewById<ImageView>(R.id.lamp2).setImageResource(R.drawable.lamp_off)
        findViewById<ImageView>(R.id.lamp3).setImageResource(R.drawable.lamp_off)
        lampOn = false
        lampNum = 1
        button.visibility = View.INVISIBLE
    }
}