package cern.be.ics.predicate;

import com.mysema.query.types.expr.BooleanExpression;
import cz.jirutka.rsql.parser.ast.ComparisonOperator;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author Justin Lewis Salmon
 */
public class PredicateBuilder<T> {
  private List<SearchCriteria> params = new ArrayList<>();

  private final Class<T> klass;

  public PredicateBuilder(Class<T> klass) {
    this.klass = klass;
  }

  public PredicateBuilder with(String key, ComparisonOperator operation, List<String> arguments) {
    params.add(new SearchCriteria(key, operation, arguments));
    return this;
  }

  public BooleanExpression build() {
    if (params.size() == 0) {
      return null;
    }

    List<BooleanExpression> predicates = new ArrayList<>();
    Predicate<T> predicate;
    for (SearchCriteria param : params) {
      predicate = new Predicate<>(param, klass);
      BooleanExpression exp = predicate.getPredicate();
      if (exp != null) {
        predicates.add(exp);
      }
    }

    BooleanExpression result = predicates.get(0);
    for (int i = 1; i < predicates.size(); i++) {
      result = result.and(predicates.get(i));
    }
    return result;
  }
}
