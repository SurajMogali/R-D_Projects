




function createTable()
{
   var table= document.createElement('table');
   var tr1 = document.createElement('tr');
   var tr2 = document.createElement('tr');
   var tr3 = document.createElement('tr');
   var tr4 = document.createElement('tr');
   

   //The created rows are added to trhe table
   table.appendChild(tr1);
   table.appendChild(tr2);
   table.appendChild(tr3);
   table.appendChild(tr4);  

   var th1 = document.createElement('th');
   var th2 = document.createElement('th');
   var th3 = document.createElement('th');

   th1.innerText="ID";
   th2.innerText="NAME";
   th3.innerText="EMAIL";

   //First row headings are created and added in the table  
   tr1.appendChild(th1);
   tr1.appendChild(th2);
   tr1.appendChild(th3);

   table.width="800px";
   table.frame="all";
   table.rules="all";
   table.style.margin="auto";

   tr1.style.backgroundColor="black";
   tr1.style.color="white";


   var td1 = document.createElement('td');
   var td2 = document.createElement('td');
   var td3 = document.createElement('td');
   var td4 = document.createElement('td');
   var td5 = document.createElement('td');
   var td6 = document.createElement('td');
   var td7 = document.createElement('td');
   var td8 = document.createElement('td');
   var td9 = document.createElement('td');

   tr2.appendChild(td1);
   tr2.appendChild(td2);
   tr2.appendChild(td3);

   tr3.appendChild(td4);
   tr3.appendChild(td5);
   tr3.appendChild(td6);

   tr4.appendChild(td7);
   tr4.appendChild(td8);
   tr4.appendChild(td9);





   td1.innerText="101";
   td2.innerText="Suraj";
   td3.innerText="mogalisuraj2000@gmail.com";

   td4.innerText="102";
   td5.innerText="Sai";
   td6.innerText="sai@gmail.com";


   td7.innerText="103";
   td8.innerText="Sandesh";
   td9.innerText="sandesh@gmail.com";






   
   






   //Whenver we give it as tagsName, it will search for all tags in the HTML
   var bodyRef=document.getElementsByTagName("body");
   bodyRef[0].appendChild(table);
   console.log(bodyRef); 

   





     


}  