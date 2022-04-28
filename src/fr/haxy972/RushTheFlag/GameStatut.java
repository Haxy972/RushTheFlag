package fr.haxy972.RushTheFlag;

public enum GameStatut {

    INGAME(), INLOBBY();



    public static GameStatut statut;


    public static GameStatut getStatut(){
        return statut;
    }
    public static void setStatut(GameStatut statuts){
        statut = statuts;

    }
    public static boolean isStatut(GameStatut statuts){
        if(statut == statuts) {
            return true;
        }else{
            return false;
        }

    }


}



