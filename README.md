# HorizontalScrollMenu
Es una libreria que permite tener un menu horizontal con scroll, basado en la clase RecyclerView

##Capturas de Pantalla
| demo 1 | demo 2 | demo 3
| --- | --- | --- |
| ![] (https://cloud.githubusercontent.com/assets/15864336/23817168/0750bcb6-05bf-11e7-9095-d380da6b408d.png) | ![] (https://cloud.githubusercontent.com/assets/15864336/23817167/074d666a-05bf-11e7-8fa1-35bc96cfb1ee.png) | ![] (https://cloud.githubusercontent.com/assets/15864336/23817165/074c9154-05bf-11e7-8c8e-dbc49d31b45a.png)

| demo 4 | demo 5 | demo 6
| --- | --- | --- |
| ![] (https://cloud.githubusercontent.com/assets/15864336/23817164/074a53b2-05bf-11e7-8f33-ab0bc16cc919.png) | ![] (https://cloud.githubusercontent.com/assets/15864336/23817166/074cf022-05bf-11e7-8c73-c3417957b1c9.png) | ![] (https://cloud.githubusercontent.com/assets/15864336/23817163/074932a2-05bf-11e7-890a-c2af7c57a7cc.png)


##Instalación
en su archivo /app/build.gradle
```
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
       compile 'com.github.MorochoRochaDarwin:HorizontalScrollMenu:2.0.1'
}
```

#Agregar la vista a su Layout
```xml
  <com.darwindeveloper.horizontalscrollmenulibrary.custom_views.HorizontalScrollMenuView
        android:id="@+id/horizontal_menu"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
         />
```
Diseño completo
```xml
      <com.darwindeveloper.horizontalscrollmenulibrary.custom_views.HorizontalScrollMenuView
        android:id="@+id/horizontal_menu"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:backgroundMenuColor="#d2d2d2"
        app:icon_height="70dp"
        app:icon_width="70dp"
        app:item_backgroundColor="@color/colorPrimary"
        app:item_colorSelected="@color/colorAccent"
        app:backgroundNotifications="@color/colorAccent"
        app:item_marginBottom="4dp"
        app:item_marginLeft="4dp"
        app:item_marginRight="4dp"
        app:item_marginTop="4dp"
        app:item_textColor="#ffffff"
        app:item_textSize="15sp" />
```

##En sus Actividades o Fragments
Inicialice la vista y capture los eventos clic
```java
        HorizontalScrollMenuView horizontal_menu = (HorizontalScrollMenuView) findViewById(R.id.horizontal_menu);
        viewPager = (ViewPager) findViewById(R.id.pager);


        //agregamos los items al menu
        horizontal_menu.addItem("Home", R.drawable.home_icon, true);//este elemento se marcara como seleccionado
        horizontal_menu.addItem("Account", R.drawable.round_account);
        horizontal_menu.addItem("Message", R.drawable.close_envelope);
        horizontal_menu.addItem("Downloads", R.drawable.download);
        horizontal_menu.addItem("Calendar", R.drawable.calendarw);
        horizontal_menu.addItem("Events", R.drawable.notifications,2);

        horizontal_menu.showItems();//hacemos visible los items del menu
        
        //implementamos la interface para caputar los clic en el menu
        horizontal_menu.setOnHSMenuClickListener(new HorizontalScrollMenuView.OnHSMenuClickListener() {
            @Override
            public void onHSMClick(MenuItem menuItem, int position) {
                Toast.makeText(MainActivity.this, "item " + position, Toast.LENGTH_SHORT).show();
            }
        });
```


##Metodos
La clase HorizontalScrollMenuView dispone de los siguientes metodos para facilitar y reducir la codificacion al usuario

| Metodo | Descripción |
| --- | --- |
| void addItem(String text, int icon) | agrega un nuevo item al menu (con un texto y un icono) |
| void addItem(String text, int icon, boolean seleted) | agrega un nuevo item al menu (con un texto , un icono y la opcion de marcarlo como seleccionado) |
| addItem(String text, int icon, boolean seleted, int numNotifications) | agrega un nuevo item al menu (con un texto , un icono y la opcion de marcarlo como seleccionado, ademas mostrara un circulo en la parte superior isquierda del icono con un numero) |
| void addItem(String text, int icon, int numNotifications) | agrega un nuevo item al menu (con un texto y un icono, ademas mostrara un circulo en la parte superior isquierda del icono con un numero) |
| int getItemSeletected() | etorna  la posicion del item seleccionado en el menu |
|  void editItem(int position, String text, int icon, boolean showNotifications, int numNotifications) | edita un item del menu segun su posicion y otros parametros (vea el javadoc para mas informacion) |
| void showItems()  | este metodo se debe llamar despues de agregar los items en el menu |
| void setItemSelected(int position) | cambia el item seleccionado del menu |
| int numItems() | retorna el numero de items que tiene el menu |



###Para mas informacion vea el ejemplo de muestra.








