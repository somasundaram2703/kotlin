import android.content.Intent
import android.graphics.pdf.PdfDocument
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.TextView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import com.example.kotlin.R
import java.io.File
import kotlin.coroutines.jvm.internal.CompletedContinuation.context
import androidx.compose.material3.Text as Text1


class TransactionDetailsActivity : AppCompatActivity() {

    // example data for a transaction
    private val transactionId = "12345"
    private val recipient = "John Doe"
    private val transactionStatus = "Completed"
    private val dateSent = "May 8, 2023"
    private val youSent = "$100.00 USD"
    private val exchangeRate = "1.0"
    private val theyReceived = "$100.00 USD"
    private val total = "$100.00 USD"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_details)

        // retrieve views from layout
        val transactionIdTextView = findViewById<TextView>(R.id.transaction_id_text_view)
        val recipientTextView = findViewById<TextView>(R.id.recipient_text_view)
        val transactionStatusTextView = findViewById<TextView>(R.id.transaction_status_text_view)
        val dateSentTextView = findViewById<TextView>(R.id.date_sent_text_view)
        val youSentTextView = findViewById<TextView>(R.id.you_sent_text_view)
        val exchangeRateTextView = findViewById<TextView>(R.id.exchange_rate_text_view)
        val theyReceivedTextView = findViewById<TextView>(R.id.they_received_text_view)
        val totalTextView = findViewById<TextView>(R.id.total_text_view)
        val downloadReceiptButton = findViewById<Button>(R.id.download_receipt_button)
        val shareButton = findViewById<Button>(R.id.share_button)

        // set text for each view
        transactionIdTextView.text = transactionId
        recipientTextView.text = recipient
        transactionStatusTextView.text = transactionStatus
        dateSentTextView.text = dateSent
        youSentTextView.text = youSent
        exchangeRateTextView.text = exchangeRate
        theyReceivedTextView.text = theyReceived
        totalTextView.text = total

        // add click listener to download receipt button
        downloadReceiptButton.setOnClickListener {
            // code to download receipt goes here
        }

        // add click listener to share button
        shareButton.setOnClickListener {
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Transaction details: \nRecipient: $recipient \nTransaction status: $transactionStatus \nDate sent: $dateSent \nYou sent: $youSent \nExchange rate: $exchangeRate \nThey received: $theyReceived \nTotal: $total")
                type = "text/plain"
            }
            startActivity(Intent.createChooser(intent, "Share with"))
        }
    }
}
@Composable
@Preview
fun TransactionDetailsScreen() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text1(text = "Transaction ID: 12345", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text1(text = "Recipient: John Doe", fontSize = 16.sp, modifier = Modifier.padding(top = 8.dp))
        Text1(text = "Transaction Status: Completed", fontSize = 16.sp, modifier = Modifier.padding(top = 8.dp))
        Text1(text = "Date Sent: May 8, 2023", fontSize = 16.sp, modifier = Modifier.padding(top = 8.dp))
        Text1(text = "You Sent: $100.00 USD", fontSize = 16.sp, modifier = Modifier.padding(top = 8.dp))
        Text1(text = "Exchange Rate: 1.0", fontSize = 16.sp, modifier = Modifier.padding(top = 8.dp))
        Text1(text = "They Received: $100.00 USD", fontSize = 16.sp, modifier = Modifier.padding(top = 8.dp))
        Text1(text = "Total: $100.00 USD", fontSize = 16.sp, modifier = Modifier.padding(top = 8.dp))

        val button = Box {
            Button(onClick = { /* TODO */ }, modifier = Modifier.padding(top = 16.dp)) {
                Text1(text = "Download Receipt")
            }
        }
        Button(onClick = { /* TODO */ }, modifier = Modifier.padding(top = 8.dp)) {
            Text1(text = "Share with Recipient")
        }
    }
}

fun Button(onClick: () -> Unit, modifier: Modifier, function: () -> Unit) {
    TODO("Not yet implemented")
}
val fileName = "transaction_receipt.pdf"
val directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
val file = File(directory, fileName)

val pdfDocument = PdfDocument()
val pageInfo = PdfDocument.PageInfo.Builder(500, 800, 1).create()
val page = pdfDocument.startPage(pageInfo)
val canvas = page.canvas

// Add your content to the canvas here
canvas.drawText("Transaction Receipt", 100f, 100f, Paint())

pdfDocument.finishPage(page)
pdfDocument.writeTo(FileOutputStream(file))
pdfDocument.close()


val uri = FileProvider.getUriForFile(context, context.applicationContext.packageName + ".provider", file)
val shareIntent = Intent(Intent.ACTION_SEND)
shareIntent.type = "application/pdf"
shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
shareIntent.setPackage("com.whatsapp")
startActivity(Intent.createChooser(shareIntent, "Share receipt"))v



