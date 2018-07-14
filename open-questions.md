# Background

So far, everything I (Adam here) feel like we would want to do I'm seeing is done, or nearly so, in CIS' Embedded Check Language, or in AE (something for which I don't yet have an artifact). I'm also seeking to approach this problem in terms of hiding as much complexity from the end user as possible. Back at IETF 97, I shared [analysis on current benchmarks](https://datatracker.ietf.org/meeting/97/materials/slides-97-sacm-narrowing-information-model-01), and found that there really aren't too-too many things for us to worry about (CAVEAT: Not all Benchmark recommendations are appropriately digitized for analysis - 892 recommendations have yet to be appropriately digitized):

* 112 distinct endpoint attribute types
* 93 OVAL test types
* 18 distinct operations
* 5 data types

The [meeting notes](https://datatracker.ietf.org/meeting/97/materials/minutes-97-sacm-00) contain some additional information, but the bottom-line take-away from that discussion is that the 400 information elements (then described by the SACM information model) were likely too many.

# General

* **What are the goals of our present efforts?** Perhaps to answer (at least) the following basic questions:
  * **Is YANG, and possibly available code, appropriate for our use cases?**
  * **What are the base set of attribute types we need for assessment based on today's needs**
* **Would we really need two artifact serializations, or could we find a way to use just one?** A corollary to this question might be _can one schema support fan-out transformation into other serializations **and** still support assessment implementation?_
* **Is there still a demand for all the use cases XCCDF seeks to address?** In particular, things like groups and profiles.
  * **Do configuration guides need recommendations to be grouped?** Or, is this something that can be left to the implementer?
  * **When enterprise policy is well-defined, are profiles necessary?** Producing a Benchmark is different than producing an enterprise policy - enterprise policy can be very precise in requirements, whereas Benchmark development is, by necessity, less precise.

# ECL-related

* **Why are the Windows-related things under `evaluate`?** I really can't figure that one out.
* **How difficult would it be to go from XML to JSON for ECL?** Probably not very.
* **Would a YANG data model be sufficient?**
* **Can we decouple collection from evaluation in ECL?** It appears that ECL has these things coupled.
* **How disruptive would it be to SCAP if we revived ECL efforts?** Hopefully this would be something we could release for inclusion in SCAP 2.0
* **Would we be able to add some control-flow to ECL?** Or, would it be better to have control flow at a layer of abstraction above ECL?
* **How did CIS-CAT handle evaluation of `SQL-query` without connection information?** The OVAL independent system characteristics have connection-specific information (some of which probably should't be included).
