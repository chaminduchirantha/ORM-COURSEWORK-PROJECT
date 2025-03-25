package lk.ijse.gdse.ormcourseworkproject.Dao;

import lk.ijse.gdse.ormcourseworkproject.Dao.custome.UserDao;
import lk.ijse.gdse.ormcourseworkproject.Dao.custome.impl.*;

public class DaoFactory {
    private static DaoFactory daoFactory = new DaoFactory();
    private DaoFactory() {

    }
    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();

        }
        return daoFactory;
    }

    public enum daoType {
        USER,THERAPIST,THERAPYSESSION,PATIENT,THERAPYPROGRAMME,
    }

    public SuperDao getDao(daoType daoType) {
        switch (daoType){
            case USER:
                return new UserDaoImpl();
                case THERAPIST:
                    return new TherapistDaoImpl();
                    case THERAPYSESSION:
                        return new TherapySessionDaoImpl();
                        case PATIENT:
                            return new PatientDaoImpl();
                            case THERAPYPROGRAMME:
                                return new TherapyProgrammeDaoImpl();

        }
        return null;
    }

}
