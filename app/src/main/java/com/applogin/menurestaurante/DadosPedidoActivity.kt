package com.applogin.menurestaurante

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.applogin.menurestaurante.databinding.ActivityDadosPedidoBinding

class DadosPedidoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDadosPedidoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDadosPedidoBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        calculations()

        binding.buttonFinalizar.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            finish()
            startActivity(i)
        }

    }

    @SuppressLint("DefaultLocale")
    private fun calculations() {
        val i = intent
        val quant_cafe = i.getStringExtra("quant_cafe").toString().toInt()
        val quant_pao = i.getStringExtra("quant_pao").toString().toInt()
        val quant_chocolate = i.getStringExtra("quant_chocolate").toString().toInt()
        val preco_cafe = i.getDoubleExtra("preco_cafe", 0.0)
        val preco_pao = i.getDoubleExtra("preco_pao", 0.0)
        val preco_chocolate = i.getDoubleExtra("preco_chocolate", 0.0)
        val preco_total =
            ((quant_pao * preco_pao) + (quant_cafe * preco_cafe) + (quant_chocolate * preco_chocolate))

        val texto = "Resumo do pedido: \n" +
                "Café: $quant_cafe , Preço:R$ ${quant_cafe * preco_cafe}\n" +
                "\nChocolate: $quant_chocolate , Preço:R$ ${quant_chocolate * preco_chocolate}\n" +
                "\nPão: $quant_pao , Preço:R$ ${quant_pao * preco_pao}\n" +
                "\n" +
                "\nTOTAL:R$ $preco_total"

        binding.textResumo.setText(texto)
    }
}