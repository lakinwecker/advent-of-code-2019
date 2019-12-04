#include <iostream>
#include <sstream>
#include <vector>
#include <iterator>
#include <algorithm>
#include <numeric>

using namespace std;

int fuel(int mass) {
    int f = mass / 3 - 2;
    if (f <= 0) {
        return 0;
    } else {
        return f + fuel(f);
    }

}

int main() {
    vector<int> items;
    copy(istream_iterator<int>(cin), istream_iterator<int>(), back_inserter(items));
    transform(items.begin(), items.end(), items.begin(), fuel);
    auto total = accumulate(items.begin(), items.end(), 0);
    cout << total << endl;
}
