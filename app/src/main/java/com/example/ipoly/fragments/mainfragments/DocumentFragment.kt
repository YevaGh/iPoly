package com.example.ipoly.fragments.mainfragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.ipoly.R
import com.example.ipoly.databinding.FragmentDocumentBinding
import com.example.ipoly.databinding.FragmentLoginBinding
import com.example.ipoly.databinding.FragmentMainBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class DocumentFragment : Fragment() {
    private var _binding: FragmentDocumentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDocumentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createAndSendPdf() {
        val pdfFile = createPdfFromLayout(requireContext(), binding.root)
        pdfFile?.let { sendPdfByEmail(it) }
    }

    private fun createPdfFromLayout(context: Context, layout: View): File? {
        val pdfFile = File(context.getExternalFilesDir(null), "layout_pdf.pdf")
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(layout.width, layout.height, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas = page.canvas
        layout.measure(
            View.MeasureSpec.makeMeasureSpec(page.canvas.width, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(page.canvas.height, View.MeasureSpec.EXACTLY)
        )
        layout.layout(0, 0, layout.measuredWidth, layout.measuredHeight)
        layout.draw(canvas)
        pdfDocument.finishPage(page)

        try {
            val fileOutputStream = FileOutputStream(pdfFile)
            pdfDocument.writeTo(fileOutputStream)
            fileOutputStream.close()
            pdfDocument.close()
            return pdfFile
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(context, "Error creating PDF", Toast.LENGTH_SHORT).show()
        }
        return null
    }

    @SuppressLint("QueryPermissionsNeeded", "IntentReset")
    private fun sendPdfByEmail( pdfFile: File) {
        val uri = Uri.fromFile(pdfFile)

        val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto: khazaryaneva@mail.ru"))
        emailIntent.type = "application/pdf"
        emailIntent.putExtra(Intent.EXTRA_STREAM, uri)
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email Subject")
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email body")

        //emailIntent.putExtra(Intent.EXTRA_EMAIL, "khazaryaneva@mail.ru")
        startActivity(Intent.createChooser(emailIntent, "Send Email"))
    }
}