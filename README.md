JLPF: Java Lightweight Processing Framework
========

<img src="Images/JLPF_Logo.png?raw=true" height="120"/>

JLPF is a java based processing framework which allows the creation and execution of customized pipes and filters architectures. The communication between pipes and filters is done by using a generic bundle objects, so any kind of data can be shared between them in order to be processed (video, sound, image, etc). Additionally, JLPF provides optimized filter execution schedulers to maximize processing performance.

* Sequential scheduler: Executes every filter sequentially in the same (unique) thread. 
* Thread pool scheduler: Creates a custom size thread pool to execute the filters.
* Thread per filter: Executes each filter on a separate thread.

Composite filters containing other filters can be created.

An IO controller implementation is given to provide communication between the framework core and the data producer/consumer, such as mobile and wearable devices.

####Target
Java 1.7

####Background

Ubiquitous interaction (gaze tracking, body gesture tracking, speech recognition, etc.) techniques are among the most interesting HCI techniques for mobile and wearable systems. However, most of the existing technologies to support them require a remote computer to perform such processing due to it's high computational resource consuming characteristic. Therefore, designing and developing a light-weighted framework able to run in mobile devices could be a valuable technology.

####Usage

1. Implement custom filters by extending Filter Class. Composite filters can be implemented by extending FilterComposite class.

2. Create a ProcessingCore instance. Add created filters to the core and start by specifying the number of threads to use.

3. Implement IOProtocolReader and IOProtocolWriter interfaces. These interfaces will serve as framework input/output (i.e. wireless camera communication, network connection).

4. Implement InputReader, OutputWriter interfaces. These interfaces specify how to read and write from protocols. A default implementation is given to be used out of the box. IORWDefaultImpl can be created by giving an IOProtocolReader and an IOProtocolWriter instances to the constructor.

5. Extend and implement IOController class. This class will implement main IO execution flow. Create an instance by giving a ProcessingCore, InputReader and OutputWriter referenced. Initialize and start.

6. Have fun!

####Example

A mobile gaze tracking system implemented in top of JLPF can be found at [EyeDroid] (https://github.com/centosGit/EyeDroid).
