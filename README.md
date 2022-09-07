# File Validation Tools

A scratch maven CLI project for Hardy and Kim to mess around with PDF and TIFF validation

## Notes

* Tika detection seems OK, only lightly tested so far
* PDFBox Preflight validation is just PDF/A and is **very** strict - even the example PDF/A files I've found for download have all failed for one or more reasons!
  * This tracks, since you need proper specs for proper validation
  * Validation in this sense is different to detecting PDF corruption. A file can be perfectly readable by all readers and still not conform to strict PDF/A standards.
* Corruption detection ideas:
  * Attempt metadata extraction?
  * Attempt other operations like splitting, jumping to page (?), concatenating with known good PDF?