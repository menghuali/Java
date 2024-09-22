package myjava.reflection;

import java.util.List;

import myjava.reflection.model.Person;
import myjava.reflection.util.ColumnField;
import myjava.reflection.util.MetaModel;
import myjava.reflection.util.PrimaryKeyField;

public class PlayWithMetamodel {

    public static void main(String[] args) {
        MetaModel<?> metamodel = MetaModel.of(Person.class);

        PrimaryKeyField pk = metamodel.getPrimaryKey();
        List<ColumnField> cols = metamodel.getColumns();

        System.out.println(pk);
        System.out.println(cols);
    }

}
