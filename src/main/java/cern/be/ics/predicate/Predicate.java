package cern.be.ics.predicate;

import com.google.common.base.CaseFormat;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.path.ListPath;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathBuilder;
import com.mysema.query.types.path.StringPath;
import lombok.AllArgsConstructor;

import java.lang.reflect.Field;
import java.util.List;

import static cz.jirutka.rsql.parser.ast.RSQLOperators.*;
import static java.lang.String.format;

/**
 * @author Justin Lewis Salmon
 */
@AllArgsConstructor
public class Predicate<T> {

  private SearchCriteria criteria;

  private final Class<T> klass;

  public BooleanExpression getPredicate() {
    PathBuilder<T> entityPath = new PathBuilder<>(klass, CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, klass.getSimpleName()));

    final List<String> args = criteria.getArguments();
    String argument = args.get(0);

    if (EQUAL.equals(criteria.getOperation())) {
      Field field = getField(criteria.getKey(), klass);

      boolean isString = false, isNumeric = false;
      if (field.getType().isAssignableFrom(String.class)) {
        isString = true;
      } else if (field.getType().isAssignableFrom(Integer.class) || field.getType().isAssignableFrom(Long.class)) {
        isNumeric = true;
      }

      if (isNumeric) {
        NumberPath<Integer> path = entityPath.getNumber(criteria.getKey(), Integer.class);
        int value = Integer.parseInt(argument);
        return path.eq(value);
      }
      else if (isString) {
        StringPath path = entityPath.getString(criteria.getKey());
        return path.startsWithIgnoreCase(argument);
      }
      else {
        throw new RuntimeException(format("Field type %s is not currently supported", field.getType()));
      }
    }

    // TODO implement remaining operations
    else if (NOT_EQUAL.equals(criteria.getOperation())) {
      Field field = getField(criteria.getKey(), klass);

      boolean isString = false, isNumeric = false;
      if (field.getType().isAssignableFrom(String.class)) {
        isString = true;
      } else if (field.getType().isAssignableFrom(Integer.class) || field.getType().isAssignableFrom(Long.class)) {
        isNumeric = true;
      }

      if (isNumeric) {
        NumberPath<Integer> path = entityPath.getNumber(criteria.getKey(), Integer.class);
        int value = Integer.parseInt(argument);
        return path.ne(value);
        // return builder.notLike(root.<String>get(property), argument.toString().replace('*', '%'));
      }
      else {
        StringPath path = entityPath.getString(criteria.getKey());
        return path.notLike(argument);
      }
    }

    else if (GREATER_THAN.equals(criteria.getOperation())) {
      throw new UnsupportedOperationException("Not implemented yet");
    }

    else if (GREATER_THAN_OR_EQUAL.equals(criteria.getOperation())) {
      throw new UnsupportedOperationException("Not implemented yet");
    }

    else if (LESS_THAN.equals(criteria.getOperation())) {
      throw new UnsupportedOperationException("Not implemented yet");
    }

    else if (LESS_THAN_OR_EQUAL.equals(criteria.getOperation())) {
      throw new UnsupportedOperationException("Not implemented yet");
    }

    else if (IN.equals(criteria.getOperation())) {
      ListPath path = entityPath.getList(criteria.getKey(), String.class);
      return path.contains(criteria.getArguments());
    }

    else if (NOT_IN.equals(criteria.getOperation())) {
      throw new UnsupportedOperationException("Not implemented yet");
    }
    else {
      return null;
    }
  }

  /**
   *
   * @param fieldName
   * @param klass
   * @return
   */
  private Field getField(String fieldName, Class<T> klass) {
    Field field;
    String nestedFieldName = null;

    if (fieldName.contains(".")) {
      String[] parts = fieldName.split("\\.");
      fieldName = parts[0];
      nestedFieldName = parts[1];
    }

    try {
      field = klass.getDeclaredField(fieldName);

      if (nestedFieldName != null) {
        field = field.getType().getDeclaredField(nestedFieldName);
      }
    } catch (NoSuchFieldException e) {
      throw new RuntimeException("Error creating predicate", e);
    }

    return field;
  }
}
