
var promiseObject=new Promise(function(resolve,reject){

// resolve({
//     status:true,
//     data:[{},{}]

// });

 reject("Error due to server crash"); 
    //  status:false,
    //  data:"Error occured",            






 });




promiseObject.
then(function(successdata){
    console.log("Then",successdata)
}).
catch(function(errordata){

    console.log("Catch",errordata);

})






