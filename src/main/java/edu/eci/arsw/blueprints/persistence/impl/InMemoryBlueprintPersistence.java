/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 *
 * @author hcadavid
 */
@Component ("InMemory")
public class InMemoryBlueprintPersistence implements BlueprintsPersistence {

    private final Map<Tuple<String, String>, Blueprint> blueprints = new HashMap<>();

    public InMemoryBlueprintPersistence() {
        //load stub data
        Point[] pts = new Point[]{new Point(140, 140), new Point(115, 115)};
        Point[] pts0 = new Point[]{new Point(1, 1), new Point(1, 1), new Point(1, 1)};
        Point[] pts1 = new Point[]{new Point(1, 2), new Point(2, 1), new Point(3, 1)};
        Blueprint nbp = new Blueprint("Jose", "ISIS2",pts);
        Blueprint nbp1 = new Blueprint("Alejandra", "ICIV2",pts0);
        Blueprint nbp2 = new Blueprint("Paula", "IIND2",pts1);
        Blueprint nbp3 = new Blueprint("Natalia", "IMEC2",pts1);
        blueprints.put(new Tuple<>(nbp.getAuthor(), nbp.getName()), nbp);
        blueprints.put(new Tuple<>(nbp1.getAuthor(), nbp1.getName()), nbp1);
        blueprints.put(new Tuple<>(nbp2.getAuthor(), nbp2.getName()), nbp2);
        blueprints.put(new Tuple<>(nbp3.getAuthor(), nbp3.getName()), nbp3);
        

    }

    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(), bp.getName()))) {
            throw new BlueprintPersistenceException("The given blueprint already exists: " + bp);
        } else {
            blueprints.put(new Tuple<>(bp.getAuthor(), bp.getName()), bp);
        }
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        if (blueprints.containsKey(new Tuple<>(author, bprintname))) {
            return blueprints.get(new Tuple<>(author, bprintname));

        } else {
            throw new BlueprintNotFoundException("The given blueprint doensn't exists: " + bprintname);

        }
    }

    @Override
    public ArrayList<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        ArrayList<Blueprint> newsBluePrints = new ArrayList<Blueprint>();
        for (Blueprint bp : blueprints.values()) {
            if (bp.getAuthor() == author) {
                newsBluePrints.add(bp);
            }
        }
        if (newsBluePrints.size() == 0) {
            throw new BlueprintNotFoundException("The given Author doesn't exists: " + author);
        } else {
            return newsBluePrints;
        }
    }

    @Override
    public ArrayList<Blueprint> getAllBlueprints() throws BlueprintNotFoundException {
        ArrayList<Blueprint> newsBluePrints = new ArrayList<Blueprint>();
        for (Blueprint bp : blueprints.values()) {

            newsBluePrints.add(bp);

        }
        if (newsBluePrints.size() == 0) {
            throw new BlueprintNotFoundException("Theres not blueprints " );
        } else {
            return newsBluePrints;
        }
    }

}
