package meuprimeiroapp.titopetri.com.imagensfelpudo;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String[] listaNomes = {"Felpudo","Fofura","Lesmo",
            "Bugado","Uruca","Racing",
            "iOS","Android","RealidadeAumentada",
            "Sound FX","3D Studio Max","Games"};

    int[] listaIcones = {R.drawable.felpudo,R.drawable.fofura,R.drawable.lesmo,
            R.drawable.bugado,R.drawable.uruca,R.drawable.carrinho,
            R.drawable.ios,R.drawable.android,R.drawable.realidade_aumentada,
            R.drawable.sound_fx,R.drawable.max,R.drawable.games};

    String[] listaDescricoes = {"Este é o protagonista dos nossos cursos de iOS e Android. Ele vive se metendo em muitas aventuras.",
            "Este é o protagonista dos nossos cursos de iOS e Android. Ele vive se metendo em muitas aventuras.",
            "Este é o protagonista dos nossos cursos de iOS e Android. Ele vive se metendo em muitas aventuras.",
            "Este é o protagonista dos nossos cursos de iOS e Android. Ele vive se metendo em muitas aventuras.","Este é o protagonista dos nossos cursos de iOS e Android. Ele vive se metendo em muitas aventuras.",
            "Este é o protagonista dos nossos cursos de iOS e Android. Ele vive se metendo em muitas aventuras.",
            "Este é o protagonista dos nossos cursos de iOS e Android. Ele vive se metendo em muitas aventuras.",
            "Este é o protagonista dos nossos cursos de iOS e Android. Ele vive se metendo em muitas aventuras.","Este é o protagonista dos nossos cursos de iOS e Android. Ele vive se metendo em muitas aventuras.",
            "Este é o protagonista dos nossos cursos de iOS e Android. Ele vive se metendo em muitas aventuras.",
            "Este é o protagonista dos nossos cursos de iOS e Android. Ele vive se metendo em muitas aventuras.",
            "Este é o protagonista dos nossos cursos de iOS e Android. Ele vive se metendo em muitas aventuras."};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView minhaLista = findViewById(R.id.minhaLista);

        final MeuAdaptador meuAdaptador;
        meuAdaptador = new MeuAdaptador(getApplicationContext(), R.layout.minha_celula);

        int i = 0;
        for(String nome:listaNomes){
            DadosPersonagem dadosPersonagem;
            dadosPersonagem = new DadosPersonagem(listaIcones[i],nome,listaDescricoes[i]);
            meuAdaptador.add(dadosPersonagem);
            i++;
        }

        minhaLista.setAdapter(meuAdaptador);

        minhaLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Toast.makeText(MainActivity.this, ""+listaNomes[i], Toast.LENGTH_SHORT).show();

                DadosPersonagem dadosPersonagem;
                dadosPersonagem = (DadosPersonagem) meuAdaptador.getItem(i);

                Intent intent = new Intent(MainActivity.this, SegundaTela.class);
                intent.putExtra("nome", dadosPersonagem.getNome());
                intent.putExtra("descricao", dadosPersonagem.getDescricao());
                intent.putExtra("icone", dadosPersonagem.getIcone());
                startActivity(intent);
            }
        });
    }


}

class ViewPersonagem{
    ImageView icone;
    TextView titulo;
    TextView descricao;
}

class DadosPersonagem{
    private int icone;
    private String titulo;
    private String descricao;

    public DadosPersonagem(int icone, String titulo, String descricao) {
        this.icone = icone;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public int getIcone() {
        return icone;
    }

    public String getNome() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}

class MeuAdaptador extends ArrayAdapter{

    public MeuAdaptador(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View minhaView;
        minhaView = convertView;
        ViewPersonagem viewPersonagem;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            minhaView = inflater.inflate(R.layout.minha_celula,parent,false);

            viewPersonagem = new ViewPersonagem();
            viewPersonagem.icone = (ImageView) minhaView.findViewById(R.id.imgIcone);
            viewPersonagem.titulo = (TextView) minhaView.findViewById(R.id.meuNome);
            //viewPersonagem.descricao = (TextView) minhaView.findViewById(R.id.meuDescr);

            minhaView.setTag(viewPersonagem);

        }else {
            viewPersonagem = (ViewPersonagem) minhaView.getTag();
        }

        DadosPersonagem dadosPersonagem;
        dadosPersonagem = (DadosPersonagem)this.getItem(position);

        viewPersonagem.icone.setImageResource(dadosPersonagem.getIcone());
        viewPersonagem.titulo.setText(dadosPersonagem.getNome());
       // viewPersonagem.descricao.setText(dadosPersonagem.getDescricao());

        return minhaView;
    }
}
