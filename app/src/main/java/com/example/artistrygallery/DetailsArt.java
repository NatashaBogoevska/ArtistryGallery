package com.example.artistrygallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsArt extends AppCompatActivity {

    String[] artistP = {"Vincent van Gogh","Leonardo da Vinci","Johannes Vermeer","Gustav Klimt"};

    String[] description = {"Vincent Van Gogh’s most popular painting, The Starry Night was created by Van Gogh at the asylum in Saint-Rémy, where he’d committed himself in 1889. Indeed, The Starry Night seems to reflect his turbulent state of mind at the time, as the night sky comes alive with swirls and orbs of frenetically applied brush marks springing from the yin and yang of his personal demons and awe of nature.",
    "Painted between 1503 and 1517, Da Vinci’s alluring portrait has been dogged by two questions since the day it was made: Who’s the subject and why is she smiling? A number of theories for the former have been proffered over the years: That she’s the wife of the Florentine merchant Francesco di Bartolomeo del Giocondo (ergo, the work’s alternative title, La Gioconda); that she's Leonardo’s mother, Caterina, conjured from Leonardo's boyhood memories of her; and finally, that it's a self-portrait in drag. As for that famous smile, its enigmatic quality has driven people crazy for centuries. Whatever the reason, Mona Lisa’s look of preternatural calm comports with the idealized landscape behind her, which dissolves into the distance through Leonardo’s use of atmospheric perspective.",
    "Johannes Vermeer’s 1665 study of a young woman is startlingly real and startlingly modern, almost as if it were a photograph. This gets into the debate over whether or not Vermeer employed a pre-photographic device called a camera obscura to create the image. Leaving that aside, the sitter is unknown, though it’s been speculated that she might have been Vermeer's maid. He portrays her looking over her shoulder, locking her eyes with the viewer as if attempting to establish an intimate connection across the centuries. Technically speaking, Girl isn’t a portrait, but rather an example of the Dutch genre called a tronie—a headshot meant more as still life of facial features than as an attempt to capture a likeness.",
    "Opulently gilded and extravagantly patterned, The Kiss, Gustav Klimt’s fin-de-siècle portrayal of intimacy, is a mix of Symbolism and Vienna Jugendstil, the Austrian variant of Art Nouveau. Klimt depicts his subjects as mythical figures made modern by luxuriant surfaces of up-to-the moment graphic motifs. The work is a highpoint of the artist’s Golden Phase between 1899 and 1910 when he often used gold leaf—a technique inspired by a 1903 trip to the Basilica di San Vitale in Ravenna, Italy, where he saw the church’s famed Byzantine mosaics."};
    TypedArray imges=null;

    String[] artistC={"Michelangelo", "Alexandros of Antioch", "Unknown" , "Agesander, Polydorus, and Athenadoros"};

    String[] descriptionC={"Created by Michelangelo between 1501-1504, David is a magnificent marble sculpture standing at an impressive 17 feet tall. This masterpiece of Italian Renaissance sculpture is an iconic representation of the Biblical hero, portrayed as a standing male nude. Commissioned by the Opera del Duomo for the Cathedral of Florence, David depicts his victory over Goliath using a sling and beheading him with his own sword.",
    "Venus de Milo is a renowned ancient Greek sculpture created by Alexandros of Antioch in the late 2nd century BCE. It stands as a larger-than-life female figure posed in a classical S-curve, and it is believed to represent Aphrodite, the goddess of love and beauty. The sculpture was discovered in pieces on the Aegean island of Melos in 1820 and presented to King Louis XVIII.\n" +
            "\n" +
            "Alexandros of Antioch was a wandering artist who worked on commission during the Hellenistic period. He crafted Venus de Milo out of white marble from Paros, Greece, using his skills for rendering complex shapes and textures into lifelike forms.",
    "The Winged Victory of Samothrace is a Greek sculpture dating back to the Hellenistic era in the beginning of the 2nd century BC. It is an exceptional piece of art and is admired for its naturalistic anatomy, creating a realistic depiction of movement. The statue was created by an unknown artist who placed Nike in an asymmetrical stance, representing her body in motion.\n" +
            "\n" +
            "This statue was excavated on the Greek island of Samothrace by Charles Champoiseau in 1863. He brought it to France and it currently resides at the Musée du Louvre in Paris.",
    "Laocoön and His Sons is a famous marble sculpture that dates back to the Hellenistic Period. It was discovered in a Roman vineyard in 1506 and is currently placed in the Vatican. The sculpture depicts Laocoön, a Trojan prince and priest, along with his young sons Antiphantes and Thymbraeus.\n" +
            "\n" +
            "What makes this sculpture exceptional is its creation by three extremely talented Greek artists from the island of Rhodes: Agesander, Polydorus, and Athenadoros."};

    TypedArray imgesC=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_art);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        Log.d("TitleDebug","Title received: "+title);

        String arText = "";
        String deText = "";
        imges=getResources().obtainTypedArray(R.array.images);
        imgesC=getResources().obtainTypedArray(R.array.imagesc);
        int imageResourceId=0;


        if(title!=null && title.equals("Starry Night")){
            imageResourceId=imges.getResourceId(0,-1);
            arText=artistP[0];
            deText=description[0];
        } else if(title!=null && title.equals("Mona Lisa")){
            imageResourceId=imges.getResourceId(1,-1);
            arText=artistP[1];
            deText=description[1];
        } else if(title!=null && title.equals("Girl With A Pearl Earring")){
            imageResourceId=imges.getResourceId(2,-1);
            arText=artistP[2];
            deText=description[2];
        } else if(title!=null && title.equals("The Kiss")){
            imageResourceId=imges.getResourceId(3,-1);
            arText=artistP[3];
            deText=description[3];
        } else if(title!=null && title.equals("David")){
            imageResourceId=imgesC.getResourceId(0,-1);
            arText=artistC[0];
            deText=descriptionC[0];
        } else if(title!=null && title.equals("Venus De Milo")){
            imageResourceId=imgesC.getResourceId(1,-1);
            arText=artistC[1];
            deText=descriptionC[1];
        } else if(title!=null && title.equals("Winged Victory Of Samothrace")){
            imageResourceId=imgesC.getResourceId(2,-1);
            arText=artistC[2];
            deText=descriptionC[2];
        } else if(title!=null && title.equals("Laocoön And His Sons")){
            imageResourceId=imgesC.getResourceId(3,-1);
            arText=artistC[3];
            deText=descriptionC[3];
        }


        ImageView img=(ImageView) findViewById(R.id.recipeImage);
        img.setImageResource(imageResourceId);
        TextView tit = (TextView) findViewById(R.id.recipeTitle);
        tit.setText(title);
        TextView ing = (TextView) findViewById(R.id.ingAll);
        ing.setText(arText);
        TextView desc = (TextView) findViewById(R.id.recipePreparation);
        desc.setText(deText);
    }
}