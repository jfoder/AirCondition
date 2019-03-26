package acs.visitors;

import acs.entities.Station;

import java.util.List;

/**
 * Visitor class that is used to perform actions on selected data
 */
public interface ISystemVisitor {
    /**
     * @param stations selected stations on which visitor will perform action
     */
    void accept(List<Station> stations);
}
