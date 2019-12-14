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
};