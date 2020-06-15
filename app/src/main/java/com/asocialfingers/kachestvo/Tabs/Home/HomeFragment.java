package com.asocialfingers.kachestvo.Tabs.Home;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.asocialfingers.kachestvo.Main.MainActivity;
import com.asocialfingers.kachestvo.R;

import org.w3c.dom.Text;

import java.util.Objects;
import java.util.Random;

public class HomeFragment extends Fragment {

    private ImageButton _open, _img1, _img2, _img3, _img4, _img5, _img6, _img7, _img8;
    private TextView _subject, _description;
    private LinearLayout _titleBar;
    private Dialog _customDialog;
    private ImageButton _close;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        methodCleaner(root);

        return root;
    }

    private void methodCleaner(View root) {

        utils(root);
        barEvents();
        colorEvents();
        clickEvents();

    }

    private void utils(View root) {

        _titleBar = (LinearLayout) root.findViewById(R.id.titleLayout);

        _open = (ImageButton) root.findViewById(R.id.showTitle);
        _img1 = (ImageButton) root.findViewById(R.id.img1);
        _img2 = (ImageButton) root.findViewById(R.id.img2);
        _img3 = (ImageButton) root.findViewById(R.id.img3);
        _img4 = (ImageButton) root.findViewById(R.id.img4);
        _img5 = (ImageButton) root.findViewById(R.id.img5);
        _img6 = (ImageButton) root.findViewById(R.id.img6);
        _img7 = (ImageButton) root.findViewById(R.id.img7);
        _img8 = (ImageButton) root.findViewById(R.id.img8);

        _customDialog = new Dialog(getActivity());

    }

    private void barEvents() {

        _open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float _translation, _rotation;

                _translation = _titleBar.getTranslationX();
                _rotation = _open.getRotation();

                _open.setRotation(180);
                _titleBar.setTranslationX(30);

                if (_rotation == 180) {
                    _open.setRotation(0);
                }

                if (_translation == 30) {
                    _titleBar.setTranslationX(1000);
                }
            }
        });

    }

    private void colorEvents() {

        Random random = new Random();
        int color1 = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        int color2 = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        int color3 = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        int color4 = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        int color5 = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        int color6 = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        int color7 = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        int color8 = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));

        //region Drawable
        GradientDrawable g1 = new GradientDrawable();
        g1.setCornerRadius(10);
        g1.setColor(color1);

        GradientDrawable g2 = new GradientDrawable();
        g2.setCornerRadius(10);
        g2.setColor(color2);

        GradientDrawable g3 = new GradientDrawable();
        g3.setCornerRadius(10);
        g3.setColor(color3);

        GradientDrawable g4 = new GradientDrawable();
        g4.setCornerRadius(10);
        g4.setColor(color4);

        GradientDrawable g5 = new GradientDrawable();
        g5.setCornerRadius(10);
        g5.setColor(color5);

        GradientDrawable g6 = new GradientDrawable();
        g6.setCornerRadius(10);
        g6.setColor(color6);

        GradientDrawable g7 = new GradientDrawable();
        g7.setCornerRadius(10);
        g7.setColor(color7);

        GradientDrawable g8 = new GradientDrawable();
        g8.setCornerRadius(10);
        g8.setColor(color8);
        //endregion

        _img1.setBackground(g1);
        _img2.setBackground(g2);
        _img3.setBackground(g3);
        _img4.setBackground(g4);
        _img5.setBackground(g5);
        _img6.setBackground(g6);
        _img7.setBackground(g7);
        _img8.setBackground(g8);

    }

    private void clickEvents() {

        _img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _title = "EFQM Nedir ?";
                String _description = "“EFQM Mükemmellik Modeli”; Avrupa Kalite Yönetimi Vakfı tarafından oluşturulmuş bir Kalite Yönetim Modelidir." +
                        "EFQM Mükemmellik Modeli, kuruluşların mükemmellik yolunda ilerleyip ilerlemediklerini ölçerek, kuvvetli yönlerini ve iyileştirmeye açık alanlarını" +
                        " görmelerini sağlayan,  uygun çözümler bulmalarında rehberlik eden, bir öz değerlendirme aracıdır." +
                        " kalitenin kabulünü hızlandırarak ve kalite geliştirme aktivitelerini destekleyerek dünya pazarında Avrupa şirketlerinin yerini güçlendirmektir.";
                customPopUp(_title, _description);
            }
        });

        _img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _title = "Özdeğerlendirme Nedir ?";
                String _description = "BİR KURULUŞUN FAALİYETLERİNİ VE İŞ SONUÇLARINI, EFQM MÜKEMMELLİK MODELİNİ ESAS ALAN BİR MODELLE KIYASLAYARAK, KAPSAMLI, SİSTEMATİK VE DÜZENLİ OLARAK GÖZDEN GEÇİRME FAALİYETİDİR.\n";
                customPopUp(_title, _description);
            }
        });

        _img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _title = " Üç Unsur ?";
                String _description = "1-)Mükemmelliğin Temel Kavramları: Herhangi bir kuruluşun sürdürülebilir mükemmelliğe erişebilmesi sürecinin temelinde yatan ilkeler\n" +
                        "2-)EFQM Mükemmellik Modeli: Kuruluşların Temel Kavramları ve RADAR uygulamasına yardımcı olacak çerçeve.\n" +
                        "3-)RADAR: Sürdürülebilir başarıyı elde etmeyi amaçlayan bir kuruluşun, bu yolda karşılaştığı güçlükleri aşması için kuruluşu desteklemek üzere kullanılabilecek dinamik bir değerlendirme çerçevesi ve güçlü bir yönetim aracı\n";
                customPopUp(_title, _description);
            }
        });

        _img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _title = "Kriterler";
                String _description = "Kriterler 9 Alt Başlıkdadır : \n"+
                        "Liderlik, Politika ve Strateji, Çalışanlar, İşbirlikleri ve Kaynaklar, Süreçler, Müşterilerle ilgili sonuçlar, çalışanlarla ilgili sonuçlar, toplumla ilgili sonuçlar, temel performans sonuçları ";
                customPopUp(_title, _description);
            }
        });

        _img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _title = "Temel Kavramlar";
                String _description = "Sonuçlara Odaklanma \n" +
                        " Müşteri Odaklılık \n" +
                        " Liderlik ve Amaç Tutarlılığı \n" +
                        " Süreçlerle ve Verilerle Yönetim \n" +
                        " Çalışanın Gelişimi ve Katılımı \n" +
                        " Sürekli Öğrenme/Gelişme/Yenilikçilik \n" +
                        " İşbirliklerinin Gelişmesi \n" +
                        " Toplumsal Sorumluluk\n";
                customPopUp(_title, _description);
            }
        });

        _img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _title = "Radar";
                String _description = "Herhangi bir kuruluşun performansını sorgulamak amacıyla yapısal bir yaklaşım sağlayan dinamik bir değerlendirme çerçevesi ve güçlü bir yönetim aracıdır. \n" +
                        "     Stratejisinin bir parçası olarak gerçekleştirmesi gereken sonuçları belirlemek için ;\n" +
                        " Gereken sonuçları hem mevcut durumda hem de gelecekte gerçekleştirmek amacıyla birbiriyle bütünleşik yaklaşımlar planlamak ve oluşturmak.\n" +
                        " Uygulamayı güvence altına almak üzere yaklaşımları yaymak\n" +
                        " Elde edilen sonuçların izlenmesine, analizine ve sürekli öğrenme faaliyetlerine dayanarak değerlendirmek ve iyileştirmek. ";
                customPopUp(_title, _description);
            }
        });

        _img7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _title = "Radar Ölçüm Yönetmi";
                String _description = "Modelin ölçüme baz, yukarıda belirtilen her bir alt kriter kapsamında ele alınan yazılı tesbitleri sonrasında kullanılan, RADAR adı verilen, etkili sayısal bir ölçüm yöntemi mevcuttur. Kısaltması RADAR olarak verilen ölçüm yönteminin açılımı aşağıdaki gibidir.\n";
                customPopUp(_title, _description);
            }
        });

        _img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _title = "Yaklaşım ve Yayılım";
                String _description = "YAKLAŞIM: Kuruluşun yapmayı planladıklarını ve bunların nedenlerini kapsar.\n" +
                        "YAYILIM: Kuruluşun bir yaklaşımı yaymak için ne yapması gerektiğini içerir. Yaklaşımlar kapsamında değişikliklerin yönetilebilmesi de uygun bir zaman çerçevesi içinde gerçekleştirilir.\n";
                customPopUp(_title, _description);
            }
        });

    }

    private void customPopUp(String _title_, String _description_) {

        _customDialog.setContentView(R.layout.custom_subject_modal);

        _description = (TextView) _customDialog.findViewById(R.id.txtDescription);
        _subject = (TextView) _customDialog.findViewById(R.id.txtSubject);
        _close = (ImageButton) _customDialog.findViewById(R.id.imgClose);

        _close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _customDialog.dismiss();
            }
        });

        _description.setText(_description_);
        _subject.setText(_title_);

        Objects.requireNonNull(_customDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        _customDialog.show();
    }
}