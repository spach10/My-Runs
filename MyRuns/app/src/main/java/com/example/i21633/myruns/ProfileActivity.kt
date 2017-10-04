package com.example.i21633.myruns

import android.app.Activity
import android.app.Fragment
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_profile.*
import kotlin.system.exitProcess
import android.provider.MediaStore
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Base64
import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.Toast
import java.io.*

class ProfileActivity : AppCompatActivity() {

    private val REQUEST_IMAGE_CAPTURE = 1
    private val PIC_CROP = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        restoreProfile()
        setClickListeners()
    }

    private fun setClickListeners() {
        save_button.setOnClickListener({ v -> saveProfile(v) })
        cancel_button.setOnClickListener({ v -> cancelProfile(v) })
        changeImage_button.setOnClickListener({ v -> changeProfilePhoto(v) })
    }

    private fun saveProfile(view: View) {
        val sharedPref = getSharedPreferences("MyRuns1", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        val bitmap = (profile_image.drawable as BitmapDrawable).bitmap
        //saveToInternalStorage(bitmap)
        editor.putString("pic", bitmapToString(bitmap))
        editor.putString("name", name_input.text.toString())
        editor.putString("email", email_input.text.toString())
        editor.putString("phone", phone_input.text.toString())
        val gender = gender.checkedRadioButtonId
        editor.putString("gender", gender.toString())
        editor.putString("class", class_input.text.toString())
        editor.putString("major", major_input.text.toString())
        editor.commit()
    }

    private fun restoreProfile() {
        val sharedPrefs = getSharedPreferences("MyRuns1", Context.MODE_PRIVATE)
        if (sharedPrefs.contains("pic")) {
            val bitmap = stringToBitMap(sharedPrefs.getString("pic", ""))
            profile_image.setImageBitmap(bitmap)
        }
        //loadImageFromStorage("/data/data/ProfilePage/app_data/imageDir")
        name_input.setText(sharedPrefs.getString("name", ""))
        email_input.setText(sharedPrefs.getString("email", ""))
        phone_input.setText(sharedPrefs.getString("phone", ""))
        gender.check(sharedPrefs.getString("gender", "1").toInt())
        class_input.setText(sharedPrefs.getString("class", ""))
        major_input.setText(sharedPrefs.getString("major", ""))
    }

    private fun cancelProfile(view: View) {
        exitProcess(1)
    }

    private fun changeProfilePhoto(view: View) {
        dispatchTakePictureIntent()
    }

    private fun dispatchTakePictureIntent() {
        // Create the intent (open the camera)
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            // Start the intent and wait for the result (data)
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // Make sure an image is captured and the result code is okay
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val picUri = data!!.data
            performCrop(picUri)
        } else if (requestCode == PIC_CROP) {
            //get the returned data
            val extras = data!!.extras
            //get the cropped bitmap
            val thePic = extras.getParcelable<Bitmap>("data")
            profile_image.setImageBitmap(thePic)
        }
    }

    private fun performCrop(picUri: Uri) {
        try {
            //call the standard crop action intent (the user device may not support it)
            val cropIntent = Intent("com.android.camera.action.CROP")
            //indicate image type and Uri
            cropIntent.setDataAndType(picUri, "image/*")
            //set crop properties
            cropIntent.putExtra("crop", "true")
            //indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1)
            cropIntent.putExtra("aspectY", 1)
            //indicate output X and Y
            cropIntent.putExtra("outputX", 256)
            cropIntent.putExtra("outputY", 256)
            //retrieve data on return
            cropIntent.putExtra("return-data", true)
            //start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, PIC_CROP)
        }
        catch(anfe: ActivityNotFoundException) {
            //display an error message
            val errorMessage = "Whoops - your device doesn't support the crop action!"
            val toast = Toast.makeText (this, errorMessage, Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    private fun bitmapToString(bitmap: Bitmap): String? {
        try {
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
            val b = baos.toByteArray()
            return Base64.encodeToString(b, Base64.DEFAULT)
        } catch (e: NullPointerException) {

        } catch (e: OutOfMemoryError) {

        }
        return null
    }

    private fun stringToBitMap(encodedString: String): Bitmap? {
        try {
            val encodeByte = Base64.decode(encodedString, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.size)
        } catch (e: Exception) {
            e.message
            return null
        }

    }

}
