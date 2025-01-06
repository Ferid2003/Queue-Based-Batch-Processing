<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#queue-based-batch-processing">About The Project</a>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation-and-usage">Installation and Usage</a></li>
      </ul>
    </li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>

# Queue Based Batch Processing

Queue Based Batch Processing (QBBP) is a processing methodology that is based on the <a href="https://jenkov.com/tutorials/java-concurrency/producer-consumer.html">Producer-Consumer pattern</a>. 
QBBP works similar to conbination of Parallel and Batch processing methodologies, using Java threads and batches to function. Created QBBP methodology was compared with other methodologies (Batch and Parallel Batch) to determine which criteria QBBP excels in.
To conduct tests 4 different searching/indexing algorithms as well as 4 different sized datasets/files were used. Full tests results and the testing criteria can be found in the Theis_Results.xlsx file.

<a href="#acknowledgments">About the Algorithms and Datasets/Files</a>

## Getting Started

To utilize the methodology and the algorithms please follow the instructions listed below.

### Prerequisites

To use the QBBP methodology one needs the data/file that is suitable for processing. Or the original datasets listed in <a href="#acknowledgments">About the Algorithms and Datasets/Files</a> can be used. This can be file of any size as long as it is considered <a href="https://en.wikipedia.org/wiki/Text_file">text file</a>.
No additional libraries are required to run the program.

### Installation and Usage

_Below is an example of how you can install the project on your local machine._

1. Clone the repo
   ```sh
   git clone https://github.com/Ferid2003/Queue-Based-Batch-Processing.git
   ```
2. Change the Output and Input File location. See commented lines in project
   
3. Select desired searching algorithm for processing. Currently there are 4 algorithms:
   * <a href="https://en.wikipedia.org/wiki/Knuth–Morris–Pratt_algorithm"> Knuth-Morris-Pratt</a>
   * <a href="https://en.wikipedia.org/wiki/Boyer–Moore_string-search_algorithm"> Boyer Moore</a>
   * <a href="https://en.wikipedia.org/wiki/Two-way_string-matching_algorithm">Two-way String-Matching</a>
   * <a href="https://en.wikipedia.org/wiki/Rabin–Karp_algorithm">Rabin Karp</a>
   
4. Run the program to see the results in specified Output File.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Contact

Farid Aghazada  - Farid.Aghazada@edu.rtu.lv

Project Link: [https://github.com/Ferid2003/Queue-Based-Batch-Processing](https://github.com/Ferid2003/Queue-Based-Batch-Processing)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Acknowledgments

Implementations of the searching and indexing algorithms as well as datastest/files that the tests were conducted are not created by the author. You can find the original implementations of the algorithms and the original datasets/files below:

## Algorithms
  * <a href="https://www.geeksforgeeks.org/kmp-algorithm-for-pattern-searching/"> Knuth-Morris-Pratt</a>
  * <a href="https://www.geeksforgeeks.org/boyer-moore-algorithm-for-pattern-searching/"> Boyer Moore</a>
  * <a href="https://gist.github.com/chenggangpro/5c56048deb1a493bc36a491d73631fca">Two-way String-Matching</a>
  * <a href="https://www.geeksforgeeks.org/rabin-karp-algorithm-for-pattern-searching/">Rabin Karp</a>

## Datasets/Files
  * <a href="https://www.kaggle.com/datasets/noeyislearning/agricultural-production?select=agricultural_production_census_2002.csv">Agricultural Production</a>
  * <a href="https://www.kaggle.com/datasets/yelp-dataset/yelp-dataset?select=yelp_academic_dataset_review.json"> Yelp Dataset</a>
  * <a href="https://ffiec.cfpb.gov/data-publication/dynamic-national-loan-level-dataset/2021">Dynamic National Loan-Level Dataset</a>
  * <a href="https://github.com/logpai/loghub/tree/master/Windows">Windows Log files</a>

<p align="right">(<a href="#readme-top">back to top</a>)</p>
