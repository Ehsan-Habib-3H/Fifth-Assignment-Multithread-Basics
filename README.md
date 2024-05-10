# Fifth-Assignment-Multithread-Basics
## Tasks 📋

### Theoretical Questions(Answers) 📝 
1. **What will be printed after interrupting the thread?**
> The thread was expected to wait for 10 sec before intruption but the ```interrupte()``` method had awakened the ```SleepThread``` and that has caused an exception.<br>
 Indeed the ```finally ``` block would be executed in any case(unless the program was not terminated by OS or JVM).
> * **So the result would look like:**
> >Thread was interrupted!<br>
> >Thread will be finished here!!!
>
> **Program won't wait for 10 sec!**

2. In Java, what would be the outcome if the `run()` method of a `Runnable` object is invoked directly, without initiating it inside a `Thread` object?
>Kiddin' me? Clearly since executing the ```run()``` of the ```DirectRunnable``` class instead ```start()``` the runnable wouldn't be executed on separate thread.
> * **Output:**
> >Running in: main
>
3. Elaborate on the sequence of events that occur when the `join()` method of a thread (let's call it `Thread_0`) is invoked within the `Main()` method of a Java program.
> First things first, ain't need to assume the thread "Thread_0" since you could just ```thread.setName();``` ¯\_(ツ)_/¯<br>
The ```join()``` will make the current thread wait till the ```thread``` is done.
> * **Output:**
> >Running in: Thread-0<br>
> >Back to: main
### Practical Questions 💻
### **Hah**!