JLPF: Java Lightweight Processing Framework
========

<img src="Images/JLPF_Logo.png?raw=true" height="120"/>

BACKGROUND
--------------

Ubiquitous interaction (gaze tracking, body gesture tracking, speech recognition, etc.) techniques are among the most interesting HCI techniques for mobile and wearable systems. However, most of the existing technologies to support them require a remote computer to perform such processing due to it's high computational resource consuming characteristic. Therefore, designing and developing a light-weighted framework able to run in mobile devices could be a valuable technology.

WHAT IS JLPF?
--------------

<img src="Images/pipes_and_filters.png?raw=true"/>

JLPF is a java based processing framework which allows the creation and execution of customized pipes and filters architectures. The communication between pipes and filters is done by using a generic bundle objects (key-value pair), so any kind of data can be shared between them in order to be processed (video, sound, image, etc). Additionally, JLPF provides optimized filter execution schedulers to maximize processing performance.

* Sequential scheduler: Executes every filter sequentially in the same (unique) thread. 
* Thread pool scheduler: Creates a custom size thread pool to execute the filters.
* Thread per filter: Executes each filter on a separate thread.

Composite filters containing other filters can be created. Composites run sequentially.

HOW TO USE?
---------

#####1. Implement custom filters by extending Filter Class. Composite filters can be implemented by extending the FilterComposite class.

```java
public class SingleFilter extends Filter {

    public static final String MESSAGE = "jlpf.singleFilter.message";

    @Override
    protected Bundle execute(Bundle bundle) {
       
        String filterName = this.getFilterName();

        //Get incoming message
        String prevMessage = (String)bundle.get(TestImplFilter.MESSAGE);

        //Send bundle to the next filter
        return bundle.put(TestImplFilter.MESSAGE, prevMessage + filterName);
    }
}
```

```java
public class CompositeFilter extends FilterComposite {

    public int compositeSize(){
        return this.mFilterChildren.size();
    }

    public List<Filter> getList(){
        return this.mFilterChildren;
    }

    public HashMap<Integer , Filter> getMap(){
        return this.mFilterMap;
    }
}
```

#####2. Create a ProcessingCore instance. Add filters to the core and start it by specifying the number of threads to use.

```java
//Define the pipe's queue size
ProcessingCore core = new ProcessingCore(CORE_QUEUE_SIZE);

//Create a composite filter
FilterComposite composite = new CompositeFilter();

//Create two single filters
Filter filter1 = new SingleFilter();
filter1.setFilterName("1");

Filter filter2 = new SingleFilter();
filter2.setFilterName("2");

//Add two single filters to the composite
composite.addFilter(filter1);
composite.addFilter(filter2);

//Create a third filter
Filter filter3 = new SingleFilter();
filter2.setFilterName("3");

//Add the composite and the third filter to the core
core.addFilter(composite);
core.addFilter(filter3);

//Start the core and specify how many threads to use. JLPF will select the best execution configuration for you!
core.start(NUM_OF_THREADS);
```

#####3. Implement IOProtocolReader and IOProtocolWriter interfaces. These interfaces will serve as the input/output to the core (i.e. network connections).

###### Source -> Core

Read is iteratively called. The returned bundle from read() is queued into the core to be processed.

```java
public class ReadProtocol implements IOProtocolReader{

    @Override
    public void init(){...}

    @Override
    public Bundle read(){
        //Queue a bundle into the core
        return new Bundle();
    }

    @Override
    public void cleanup(){init();}
}

```

###### Core -> Sink

Write is iteratively called. The method receives a processed bundle from the core.

```java
public class WriteProtocol implements IOProtocolWriter{

    @Override
    public void init(){...}

    @Override
    public void write(Bundle bundle){
        //Bundle dequeued callback
        //Do something...
    }

    @Override
    public void cleanup(){init();}
}

```

``` java
//Create the I/O protocols
 IOProtocolReader Rprotocol = new ReadProtocol();
 IOProtocolWriter Wprotocol = new WriteProtocol();
```

#####4. Implement InputReader, OutputWriter interfaces. These interfaces specify how to read and write from the protocols. A default implementation is given readily to be used out of the box. IORWDefaultImpl can be created by giving an IOProtocolReader and an IOProtocolWriter instances to the constructor.

```java
 IORWDefaultImpl ioRW = new IORWDefaultImpl(Rprotocol, Wprotocol);
```

#####5. Create an IOController by giving a ProcessingCore, an InputReader and an OutputWriter references. This instance will handle the I/O core execution flow. Initialize and start.

```java
 IOController ioController = new ImplRWProtocol(core, ioRW, ioRW);
 ioController.setupController();
 ioController.start();
```

#####6. Have fun!

TARGET
---------

Java 1.7

SOFTWARE ARCHITECTURE
--------------

<img src="Images/jlpf_arch.png?raw=true" height="600"/>

USAGE EXAMPLE
---------

A mobile gaze tracking system implemented in the top of JLPF can be found at [EyeDroid] (https://github.com/centosGit/EyeDroid).
