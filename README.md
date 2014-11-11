# component-based

A Leiningen template for Stuart Sierra's [component](https://github.com/stuartsierra/component)-based projects.

## Usage

Clone the repository, cd to the project directory, run ```lein install```

```
lein new component-based $PROJECT_NAME [with-om]
```

$PROJECT_NAME can be namespaced, i.e. ```org.stask/test```, in that case, the project ```test``` will be created, and all the clojure/clojurescript namespaces will have prefix ```org.stask```.

_with\_om_ optional parameter will add [om](https://github.com/swannodette/om) support to the project.

## License

Copyright © 2014 stask.

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
