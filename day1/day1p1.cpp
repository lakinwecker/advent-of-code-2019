#include <iostream>
#include <sstream>
#include <vector>
#include <iterator>
#include <algorithm>
#include <numeric>

using namespace std;

int main() {
    vector<int> items;
    copy(istream_iterator<int>(cin), istream_iterator<int>(), back_inserter(items));
    transform(items.begin(), items.end(), items.begin(), [](auto m){ return m / 3 - 2; });
    cout << accumulate(items.begin(), items.end(), 0) << endl;
}
