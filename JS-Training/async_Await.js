function createPromise(){

    return new Promise(function(resolve,reject){
        resolve({
            status:true,
            data:[{name:"Rohan"},{name:"Sahil"},{name:"Zoya"}],

        })


    })

}

 async function catchPromise(){
    try{
        var responseData= await createPromise()
        console.log(responseData);

    }catch(errordata){
        console.log(errordata);

    }
   
   


}

catchPromise();

