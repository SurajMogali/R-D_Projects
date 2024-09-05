function fnGetData(){
   fetch("https://jsonplaceholder.typicode.com/users")
   .then((success)=>{
   return success.json();
   })
   .then((data)=>{
    var ol=document.createElement('ol')
    data.forEach((element)=>{
        var li=docment.createElement("li")
        li.innerText=element.name
        ol.appendChild(li)
    });
    document.getElementById("body").appendChild(ol);
   })
   .catch((error)=>{
    var h2=document.createElement('h2');
    h2.innerText="Something went wromg while fetching the data"
    h2.style.fontSize="52px"
    h2.style.color="red"
    document.getElementById('body').appendChild(h2)

   });
     

}