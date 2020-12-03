package com.example.adventure

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.adventure.R

class DrawView(context: Context, attrs: AttributeSet? = null) : View(context, attrs){


    val forest: Bitmap
    val mountain: Bitmap
    val out: Bitmap
    val person: Bitmap
    val plain: Bitmap
    val treasure: Bitmap
    val water: Bitmap
    val sizex = 12
    val sizey = 12
    val map = arrayOf(
        charArrayOf('M','M','M','.','.','m','t','m','m','m','`','.'),
        charArrayOf('M','M','F','F','.','`','.','m','m','m','`','.'),
        charArrayOf('M','F','F','.','`','`','.','.','.','`','`','`'),
        charArrayOf('F','F','F','m','`','`','.','.','.','`','`','`'),
        charArrayOf('.','.','`','`','m','f','.','.','.','`','`','f'),
        charArrayOf('F','F','F','.','.','.','.','.','`','`','F','F'),
        charArrayOf('F','F','F','.','.','.','.','.','`','`','F','F'),
        charArrayOf('F','F','F','.','.','.','.','.','`','`','F','F'),
        charArrayOf('M','M','M','.','.','m','t','m','m','m','`','.'),
        charArrayOf('M','M','F','F','.','`','.','m','m','m','`','.'),
        charArrayOf('.','.','`','`','m','f','.','.','.','`','`','f'),
        charArrayOf('F','F','F','.','.','.','.','.','`','`','F','F'))

    var direction = 'N'

    init {
        forest = BitmapFactory.decodeResource(
            getResources(),
            R.drawable.forest,
            null
        )
        mountain = BitmapFactory.decodeResource(
                getResources(),
        R.drawable.mountain,
        null
        )
        out = BitmapFactory.decodeResource(
            getResources(),
            R.drawable.out,
            null
        )
        person = BitmapFactory.decodeResource(
            getResources(),
            R.drawable.person,
            null
        )
        plain = BitmapFactory.decodeResource(
            getResources(),
            R.drawable.plain,
            null
        )
        treasure = BitmapFactory.decodeResource(
            getResources(),
            R.drawable.treasure,
            null
        )
        water = BitmapFactory.decodeResource(
            getResources(),
            R.drawable.water,
            null
        )

    }

    fun Direction(x : Float, y : Float) : Char{

        if(y < 500)
            return 'n'
        else if(y > 500 && y < 1200 && x < 550 )
            return 'w'
        else if(y > 500 && y < 1200 && x > 550)
            return 'e'
        else if(y > 1200)
            return 's'
        else
            return 'b'


    }

    var offsetX = 0
    var offsetY = 0


    var dir = 'e'

    fun Move(x : Float, y : Float){
        dir = Direction(x,y)

        Log.i("cs3680", "dir " + dir)

        if(dir == 'n') {
            --offsetY
            invalidate()
        }
        else if(dir == 'e'){
            ++offsetX
            invalidate()
        }
        else if(dir == 's'){
            ++offsetY
            invalidate()
        }
        else if(dir == 'w'){
            --offsetX
            invalidate()
        }


    }

    override fun onTouchEvent(event: MotionEvent):Boolean {
        if(event.action == MotionEvent.ACTION_UP){
            //gets x and y coordinates of spot clicked
            Log.i("cs3680","touch event " + event.getX() + "," + event.getY())
            Log.i("cs3680", "2d array, " + map.size)
            Move(event.getX(),event.getY())
            return true
        }
        return true
    }


    override fun onDraw(canvas: Canvas) {
        //canvas.drawBitmap(forest, 0F, 0F, null)
        //canvas.drawBitmap(mountain, 155F, 0F, null)

        val center = 2

        var i = 0
        var j = 0
        var picX = 0F
        var picY = 155F


        while(i < 5){
            picX = 155F
            while(j < 5){
                var loc = 'o'
                var upper = loc.toUpperCase()
                var testI = i + offsetY
                var testJ = j + offsetX
                var outOfMap = 12
                var offMapNeg = -3
                var offMapPos = 10

                if(testI >= 0 && testJ >= 0 && testI < outOfMap && testJ < outOfMap ) {
                    loc = map[i + offsetY][j + offsetX]
                    upper = loc.toUpperCase()
                }

                if(offsetX == offMapNeg || offsetY == offMapNeg || offsetX == offMapPos || offsetY == offMapPos){

                    if(dir == 'n')
                        offsetY++
                    else if(dir == 'e')
                        offsetX--
                    else if(dir == 's')
                        offsetY--
                    else if(dir == 'w')
                        offsetX++
                }


                Log.i("cs3680", "loc " + upper)
                if(upper == 'F')
                    canvas.drawBitmap(forest, picX, picY, null)
                else if(upper == 'M')
                    canvas.drawBitmap(mountain, picX, picY, null)
                else if(upper == '.')
                    canvas.drawBitmap(plain, picX, picY, null)
                else if(upper == '`')
                    canvas.drawBitmap(water, picX, picY, null)
                else if(upper == 'T')
                    canvas.drawBitmap(treasure, picX, picY, null)
                else if(upper == 'O')
                    canvas.drawBitmap(out,picX,picY,null)

                if(i == center && j == center)
                    canvas.drawBitmap(person,picX,picY,null)

                j++
                picX += 155F
            }
            picY += 155F
            j = 0
            i++
        }










    }

}