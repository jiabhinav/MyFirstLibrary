package com.biocube.myfirstlibrary

import android.content.Context
import android.graphics.BitmapFactory
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.biocube.myfirstlibrary.databinding.FragmentFirstBinding
import com.biocube.mylibrary.DownloadImage
import java.io.DataInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    val mFileName = "demo123.jpg"
    private var _binding: FragmentFirstBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val thread = Thread {
            try {
                DownloadImage(requireActivity(),mFileName).storeDownload(binding.image)
                DownloadImage(requireActivity(),mFileName)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        thread.start()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

  /*  private fun storeDownload(image: ImageView) {
        try {
            val u = URL("https://cdn.searchenginejournal.com/wp-content/uploads/2019/08/c573bf41-6a7c-4927-845c-4ca0260aad6b-1520x800.jpeg")
            val out = u.openStream()
            val dis = DataInputStream(out)
            val buffer = ByteArray(1024)
            var length: Int
            val fos: FileOutputStream = requireActivity().openFileOutput(mFileName, Context.MODE_PRIVATE)
            while (dis.read(buffer).also { length = it } > 0) {
                fos.write(buffer, 0, length)
            }

            val bitmap = BitmapFactory.decodeFile(getFilePath())
           image.setImageBitmap(bitmap)
            Log.d("TAG", "storeDownload: "+getFilePath())

        } catch (mue: MalformedURLException) {
            Log.e("SYNC getUpdate", "malformed url error", mue)
        } catch (ioe: IOException) {
            Log.e("SYNC getUpdate", "io error", ioe)
        } catch (se: SecurityException) {
            Log.e("SYNC getUpdate", "security error", se)
        }
    }

    private fun getFilePath(): String {
        val mFileName = mFileName
        val inputStream  = requireActivity().getFileStreamPath(mFileName)
        val filePath: String = inputStream.getAbsolutePath()
        return filePath
    }*/
}