package com.airiane.criptomoedas

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_save.*

class SaveAtivosActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener,
    View.OnClickListener {
    var nome = ""
    var codigo = ""
    var qtd = 0.0
    var id_ativo: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save)
        val spinner = findViewById<Spinner>(R.id.dropDown)


        FABBack.setOnClickListener(View.OnClickListener {

            onBackPressed()
        })


        FABSave.setOnClickListener(View.OnClickListener {

            var ativo =
                Ativo(id_ativo, nome, codigo, qtd)
            var ativoDao = AtivosDAO(this)
            var validacaoId = ativoDao.pegaId(id_ativo)
            if (validacaoId == true) {
                ativoDao.insert(ativo)
                onBackPressed()
            } else {
                val alerta =
                    AlertDialog.Builder(this@SaveAtivosActivity)
                alerta.setTitle("Aviso")
                alerta
                    .setIcon(R.drawable.ic_info_foreground)
                    .setMessage("Ativo já adicionado.")
                    .setCancelable(true)
                    .setPositiveButton(
                        "OK",
                        DialogInterface.OnClickListener({ dialogInterface, i ->
                        })
                    )


                val alertDialog = alerta.create()
                alertDialog.show()
            }
        })

        var listaDeItens = arrayOf(" Bitcoin", "Litecoin", "BCash", "XRP (Ripple)", "Ethereum")
        spinner!!.setOnItemSelectedListener(this)
        val escolha = ArrayAdapter(this, android.R.layout.simple_spinner_item, listaDeItens)
        escolha.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner!!.setAdapter(escolha)
    }

    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, posicao: Int, id: Long) {
        if (posicao == 0) {
            txt_moeda.text = "Bitcoin"
            nome = "Bitcoin"
            codigo = "BTC"
            id_ativo = 1

        }
        if (posicao == 1) {
            txt_moeda.text = "Litecoin"
            nome = "Litecoin"
            codigo = "LTC"
            id_ativo = 2
        }
        if (posicao == 2) {
            txt_moeda.text = "BCash"
            nome = "BCash"
            codigo = "BHC"
            id_ativo = 3
        }
        if (posicao == 3) {
            txt_moeda.text = "XRP"
            nome = "XRP"
            codigo = "XRP"
            id_ativo = 4
        }
        if (posicao == 4) {
            txt_moeda.text = "Ethereum"
            nome = "Ethereum"
            codigo = "ETH"
            id_ativo = 5
        }
    }

    override fun onNothingSelected(arg0: AdapterView<*>) {
    }

    override fun onClick(v: View?) {
    }


}
