package nl.avisi.demo.test;

import org.unitils.reflectionassert.difference.*;

import java.util.Map;

public class DifferenceVisitorImpl implements DifferenceVisitor<Object,Object> {

    @Override
    public Object visit(Difference difference, Object argument) {
        return argument;
    }

    @Override
    public Object visit(ObjectDifference objectDifference, Object argument) {
        Map<String, Difference> fieldDifferences = objectDifference.getFieldDifferences();
        StringBuilder builder = new StringBuilder(argument.toString());
        fieldDifferences.forEach((field, difference) -> {
            builder.append(String.format("Field \'%s\': (%s) left: \'%s\', right: \'%s\'\n", field, difference.getMessage(), difference.getLeftValue(), difference.getRightValue()));
        });

        return builder.toString();
    }

    @Override
    public Object visit(ClassDifference classDifference, Object argument) {
        return argument;
    }

    @Override
    public Object visit(MapDifference mapDifference, Object argument) {
        return argument;
    }

    @Override
    public Object visit(CollectionDifference collectionDifference, Object argument) {
        return argument;
    }

    @Override
    public Object visit(UnorderedCollectionDifference unorderedCollectionDifference, Object argument) {
        return argument;
    }

}
