Procedure :-
netbeans ide :
file-> new project-> java with maven & java application-> project_name-> source package-> new-> jframe form-> frame_name-> finish-> setup text fields with labels->button(add)-> double click->paste code-> save->run;

code :

int a,b,c;
a = Integer.parseInt(jTextField1.getText());
b = Integer.parseInt(jTextField2.getText());
c = a+b;
jTextField3.setText(" "+c);
