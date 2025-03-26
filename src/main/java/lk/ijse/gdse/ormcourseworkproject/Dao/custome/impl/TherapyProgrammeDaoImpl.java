package lk.ijse.gdse.ormcourseworkproject.Dao.custome.impl;

import lk.ijse.gdse.ormcourseworkproject.Dao.DaoFactory;
import lk.ijse.gdse.ormcourseworkproject.Dao.custome.TherapyProgrammeDao;
import lk.ijse.gdse.ormcourseworkproject.Entity.TherapyProgramme;
import lk.ijse.gdse.ormcourseworkproject.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TherapyProgrammeDaoImpl implements TherapyProgrammeDao {

    Session session = FactoryConfiguration.getInstance().getSession();
    Transaction transaction =session.beginTransaction();
    TherapyProgramme therapyProgramme = (TherapyProgramme) DaoFactory.getInstance().getDao(DaoFactory.daoType.THERAPYPROGRAMME);


    public String getNextId() throws SQLException, IOException {

        String hql = "SELECT l.therapyProgrammeId FROM TherapyProgramme l ORDER BY l.therapyProgrammeId DESC";
        Query<String> query = session.createQuery(hql);

        query.setMaxResults(1);
        String lastId = query.uniqueResult();

        transaction.commit();
        session.close();

        if (lastId != null) {
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("T%03d", newIdIndex);
        }

        return "T001";
    }
    @Override
    public List<TherapyProgramme> getAll(){
        Query<TherapyProgramme>therapyProgrammeQuery=session.createQuery("SELECT tp FROM TherapyProgramme tp", TherapyProgramme.class);
        List<TherapyProgramme>therapyProgrammeList=therapyProgrammeQuery.list();
        transaction.commit();
        session.close();
        return therapyProgrammeList;
    }

    @Override
    public boolean save(TherapyProgramme therapyProgramme){
        session.persist(therapyProgramme);
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public boolean update(TherapyProgramme therapyProgramme){
        session.merge(therapyProgramme);
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public boolean delete(String pk){
        therapyProgramme.setTherapyProgrammeId(pk);
        session.remove( therapyProgramme);
        transaction.commit();
        session.close();
        return false;
    }
}
