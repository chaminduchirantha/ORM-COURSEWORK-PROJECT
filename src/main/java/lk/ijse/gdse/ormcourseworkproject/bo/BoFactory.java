package lk.ijse.gdse.ormcourseworkproject.bo;

import lk.ijse.gdse.ormcourseworkproject.Entity.SuperEntity;
import lk.ijse.gdse.ormcourseworkproject.bo.custome.UserBo;
import lk.ijse.gdse.ormcourseworkproject.bo.custome.impl.*;

public class BoFactory {
    private static BoFactory boFactory = new BoFactory();
    private BoFactory() {

    }
    public static BoFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BoFactory();
        }
        return boFactory;
    }

    public enum boType{
        USER,THERAPIST,THERAPYSESSION,PATIENT,THERAPYPROGRAMME,PAYEMENT;
    }

    public SuperBo getBo(boType type){
        switch (type){
            case USER:
                return new UserBoImpl();
                case THERAPYSESSION:
                    return new TherapySessionBoImpl();
                    case THERAPYPROGRAMME:
                        return new TherapyProgrammeBoImpl();
                        case PATIENT:
                            return new PatientBoImpl();
                            case PAYEMENT:
                                return new PaymentBoImpl();
                                case THERAPIST:
                                    return new TherapistBoImpl();
        }
        return null;
    }
}
