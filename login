<!-- Pantalla de inicio de sesión -->
<RelativeLayout
  xmlns:android="http://schemas.android.com/apk/res/android"
  android:id="@+id/login"
  android:layout_width="361dp"
  android:layout_height="631dp"
  android:clipToOutline="true"
  android:background="#CBC8C0"/>

  <!--usuario y contaseña  -->
    <TextView
    android:id="@+id/asistencia"
    android:layout_width="176dp"
    android:layout_height="40dp"
    android:text="@string/asistencia"
    android:textAppearance="@style/asistencia"
    android:lineSpacingExtra="-6sp"
    android:gravity="center_horizontal|top"/>

    <TextView
    android:id="@+id/app"
    android:layout_width="72dp"
    android:layout_height="46dp"
    android:text="@string/app"
    android:textAppearance="@style/app"
    android:gravity="center_horizontal|top"/>

    <RelativeLayout
    android:id="@+id/mexico"
    android:layout_width="304dp"
    android:layout_height="138dp"
    android:layout_centerHorizontal="true"
    android:layout_centerVertical="true"/>

    <TextView
    android:id="@+id/bienvenido"
    android:layout_width="189dp"
    android:layout_height="46dp"
    android:text="@string/bienvenido"
    android:textAppearance="@style/bienvenido"
    android:gravity="center_horizontal|top"/>

    <TextView
    android:id="@+id/n_mero_de_e"
    android:layout_width="173dp"
    android:layout_height="21dp"
    android:text="@string/n_mero_de_e"
    android:textAppearance="@style/n_mero_de_e"
    android:gravity="center_vertical"/>

    <TextView
    android:id="@+id/contrase_a"
    android:layout_width="92dp"
    android:layout_height="21dp"
    android:text="@string/contrase_a"
    android:textAppearance="@style/contrase_a"
    android:gravity="center_vertical"
    />

    <RelativeLayout
    android:id="@+id/atom_button"
    android:layout_width="186dp"
    android:layout_height="60dp"
    android:layout_alignParentLeft="true"
    android:layout_marginLeft="87dp"
    android:layout_alignParentTop="true"
    android:layout_marginTop="508dp"
    android:background="@drawable/atom_button"
    />



<!--
  Font family: Circular Std
  Line height: 40sp
  (identical to box height)
  -->
  
<!-- styles.xml -->
<style name="asistencia">
<item name="android:textSize">36sp</item>
<item name="android:textColor">#FFFFFF</item>
</style>
  
<!-- strings.xml -->
<string name="asistencia">
  Asistencia
</string>
