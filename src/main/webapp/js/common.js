var vanillaxHelper = {
    userLoginFlag : false,
    putToken: function(token){
        if (typeof(Storage) !== "undefined") {
            // Store
            localStorage.setItem("VANILLAX-USER-TOKEN", token);
        }
    },
    getToken: function(){
        if (typeof(Storage) !== "undefined") {
            return localStorage.getItem("VANILLAX-USER-TOKEN");
        }
        return null;
    },
    hasToken: function(){
        if (typeof(Storage) !== "undefined") {
            if(localStorage.getItem("VANILLAX-USER-TOKEN")){
                return true;
            }
        }
        return false;
    },
    checkLogin: function(){
        if(!this.hasToken()){
            return false;
        }
        return false;
    },
    onError: function(err){
//        console.log(JSON.stringify(err));
        if(err.response && err.response.data && err.response.data.message ){
            if(err.response.data.code && err.response.data.code === "ERR1100"){//Login Error
                alert("You are not logged in.");
                location.replace("/");
                return;
            }
            let msg = err.response.data.message;
            if(err.response.data.detail){
                msg = msg + " / " + err.response.data.detail;
            }
            alert(msg);
        }


    }
};