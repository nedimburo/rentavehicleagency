package com.example.rentavehicleagency.users.entities;

public enum RoleType {
    USER,
    OWNER,
    DIRECTOR,
    HR,
    FINANCE,
    RENT,
    MAINTENANCE;

    public boolean user() {return this.equals(USER);}
    public boolean owner() {return this.equals(OWNER);}
    public boolean director() {return this.equals(DIRECTOR);}
    public boolean hr() {return this.equals(HR);}
    public boolean finance() {return this.equals(FINANCE);}
    public boolean rent() {return this.equals(RENT);}
    public boolean maintenance() {return this.equals(MAINTENANCE);}
}
