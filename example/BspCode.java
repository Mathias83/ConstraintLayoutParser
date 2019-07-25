package de.hs_kl.tran;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{

   public void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      createScreen();
   }

   private void createScreen()
   {
      LinearLayout screen = new LinearLayout(this);
      screen.setLayoutParams(new LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT));
      screen.setOrientation(LinearLayout.VERTICAL);
      screen.addView(createNameContainer());
      screen.addView(createAddressContainer());
      setContentView(screen);
   }

   private LinearLayout createNameContainer()
   {
      // Container erzeugen
      LinearLayout nameContainer = new LinearLayout(this);
      // Verschiedene Attribute setzen
      nameContainer.setBackgroundColor(Color.WHITE);

      // wie wird die View im Vatercontainer platziert
      nameContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT));
      // Wie werden die Kinder platziert
      nameContainer.setOrientation(LinearLayout.HORIZONTAL);

      // Kinder erzeugen und in den Container aufnehmen
      TextView nameLabel = new TextView(this);
      nameLabel.setText("Hochschule: ");
      TextView name = new TextView(this);
      name.setText("Kaiserslautern");

      nameContainer.addView(nameLabel);
      nameContainer.addView(name);
      return nameContainer;

   }

   private LinearLayout createAddressContainer()
   {
      LinearLayout adrContainer = new LinearLayout(this);
      adrContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
      adrContainer.setOrientation(LinearLayout.VERTICAL);
      TextView strLabel = new TextView(this);
      strLabel.setText("Amerikastr. 1");
      TextView stadt = new TextView(this);
      stadt.setText("66482 Zweibrücken");
      adrContainer.addView(strLabel);
      adrContainer.addView(stadt);
      return adrContainer;

   }

}
