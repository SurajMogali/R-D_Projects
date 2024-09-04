var details=[] //empty array

var userdetails=["Sahil",100,true,{city:"Hyd",state:"TS"},"male"] 
console.log(userdetails.length) ;

//Add element in the array
userdetails.push(400);
console.log(userdetails);


//Add element in the initail position
userdetails.unshift(99);
console.log(userdetails);

//Remove element from starting 
userdetails.shift();

//Remove element from ending position
userdetails.pop();

console.log(userdetails);


//arrayName.splice(startingIndex,NumberOfElementsToRemove,Elements you want to add )
userdetails.splice(2,3);

console.log(userdetails);

//add new element
userdetails.splice(2,0,{city:"delhi"});
console.log(userdetails);

//Remove certain section of the array
//arrayName.slice(startingIndex,lastIndex) //Last index will not be included in the list.
userdetails.slice(1,2);
console.log(userdetails);
 
//+++++Splice will modify the original array but slice will not+++++++++++++++//

console.log(userdetails.indexOf("Sahil"));

//Is  Present or Not 
var isElementPresent=userdetails.includes("Rahul1");
console.log(isElementPresent);

//Access the particualr element
console.log(userdetails[0]);


//forEach
userdetails.forEach(function(element,index){
console.log(element,index);
});


//Map
var arr=[12,23,34,45];
 var newArry=arr.map(function(element,index)
{
    return element*2;
  

});

//Filter
console.log(newArry);

var newArr2=arr.filter(function(element,index)
{
    return element % 2
  

});

console.log(newArr2);









 

